package com.fish.keXieOpenJudge.service.example.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.judge.*
import com.fish.keXieOpenJudge.entity.pojo.example.AssessmentResult
import com.fish.keXieOpenJudge.entity.pojo.example.ExampleResult
import com.fish.keXieOpenJudge.entity.vo.JudgeResultVO
import com.fish.keXieOpenJudge.entity.vo.TestResultVO
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.network.JudgeFeign
import com.fish.keXieOpenJudge.service.example.AssessmentResultService
import com.fish.keXieOpenJudge.service.example.ExampleResultService
import com.fish.keXieOpenJudge.service.example.ExampleService
import com.fish.keXieOpenJudge.service.example.JudgeService
import com.fish.keXieOpenJudge.service.topic.TopicService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.fish.keXieOpenJudge.utils.SecurityUtil.getId
import org.springframework.data.redis.core.script.DigestUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JudgeServiceImpl(
    val assessmentResultService: AssessmentResultService,
    val exampleResultService: ExampleResultService,
    val topicService: TopicService,
    val exampleService: ExampleService,
    val judgeFeign: JudgeFeign
): JudgeService {
    override fun runTest(runTestDTO: RunTestDTO): Result<TestResultVO> {
        val runTestResult: RunTestResultDTO
        try {
            runTestResult = judgeFeign.runTest(runTestDTO)
            return success(TestResultVO(runTestResult))
        } catch (classCaseException: ClassCastException) {
            throw ServiceException(ServiceExceptionEnum.JUDGE_SERVER_ERROR)
        }
    }

    /**
     * 构造返回测评结果实例, 用于以下场景
     * 1. 见`preJudge`, 如果存在hash值相同的, 可以直接把存在数据库中的结果和样例测试结果一并返回
     * 2. 见hash未匹配时的最后返回
     */
    fun makeJudgeResultVO(assessmentResult: AssessmentResult): JudgeResultVO{
        return JudgeResultVO(
            status = assessmentResult.status!!,
            allMemory = assessmentResult.allMemory!!,
            allTime = assessmentResult.allTime!!,
            exampleResult = exampleService.getExampleVoByResultId(assessmentResult.resultId!!)
        )
    }

    /**
     * 构造Feign的参数, 用于向沙箱服务发请求
     */
    fun makeFeignParameter(judgeDTO: JudgeDTO): RunJudgeDTO {
        val examples = exampleService.getExamplesById(judgeDTO.topicId)
        val topic = topicService.getById(judgeDTO.topicId)
        val cases = ArrayList<Case>()
        for(i in examples.indices) {
            cases.add(Case(examples[i]))
        }
        return RunJudgeDTO(
            code = judgeDTO.code,
            lang = judgeDTO.lang,
            memory = topic.limitedMemory!!,
            outMsgLimit = judgeDTO.outMsgLimit,
            time = topic.limitedTime!!,
            case = cases
        )
    }

    /**
     * **重要**
     *
     * 在正式判题之前，先从数据库中检查hash
     */
    fun preJudge(judgeDTO: JudgeDTO, hash: String): JudgeResultVO? {
        val assessmentResultFromMatch = assessmentResultService.matchHashByTopicId(judgeDTO.topicId, hash)
        assessmentResultFromMatch?.let {
            return makeJudgeResultVO(it)
        }
        return null
    }

    fun makeAssessmentResult(judgeDTO: JudgeDTO, judgeResultDTO: RunJudgeResultDTO, hash: String): AssessmentResult {
        val id = getId()
        return AssessmentResult(id, hash, judgeDTO, judgeResultDTO)
    }

    fun makeExampleResultList(assessmentResult: AssessmentResult, judgeResultDTO: RunJudgeResultDTO): ArrayList<ExampleResult> {
        val resultId = assessmentResult.resultId
        val exampleResultList = ArrayList<ExampleResult>()
        judgeResultDTO.codeResults.forEach {
            val exampleResult = ExampleResult(
                resultId = resultId!!,
                exampleId = it.caseId.toLong(),
                memory = it.memoryUsage,
                cpuTime = it.cpuTimeUsage,
                realTime = it.realTimeUsage,
                assessmentStatus = it.state
            )
            exampleResultList.add(exampleResult)
        }
        return exampleResultList
    }

    @Transactional
    override fun runJudge(judgeDTO: JudgeDTO): Result<JudgeResultVO> {
         // 1. 先获取hash码
        val hash = DigestUtils.sha1DigestAsHex(judgeDTO.code)
        // 2. 匹配数据库中hash码(同一道题目的)
        val preJudgeResultVO = preJudge(judgeDTO, hash)
        // 通过是否为空来判断是否有hash值相同，若存在相同则可以直接返回
        preJudgeResultVO?.let {
            return success(preJudgeResultVO)
        }
        // 构造沙箱服务识别的参数
        val runJudgeDTO = makeFeignParameter(judgeDTO)
        val judgeResultDTO: RunJudgeResultDTO
        try {
            judgeResultDTO = judgeFeign.runJudge(runJudgeDTO)
            // 构造需要插入到数据库中的总测评结果
            val assessmentResult = makeAssessmentResult(judgeDTO, judgeResultDTO, hash)
            val addAssessmentResultFlag = assessmentResultService.addAssessmentResult(assessmentResult)
            if (addAssessmentResultFlag) {
                // 构造需要插入到数据库中的单独样例结果
                val exampleResultList = makeExampleResultList(assessmentResult, judgeResultDTO)
                val addExampleResultFlag = exampleResultService.addExampleResult(exampleResultList)
                if (addExampleResultFlag)
                    // 最后构造判题结果VO返回给前端
                    return success(makeJudgeResultVO(assessmentResult))
                throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
            }
            throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        } catch (classCaseException: ClassCastException) {
            throw ServiceException(ServiceExceptionEnum.JUDGE_SERVER_ERROR)
        }
    }
}

