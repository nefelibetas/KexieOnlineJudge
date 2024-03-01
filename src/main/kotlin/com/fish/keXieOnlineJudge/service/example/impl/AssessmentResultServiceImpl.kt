package com.fish.keXieOnlineJudge.service.example.impl

import com.fish.keXieOnlineJudge.entity.pojo.example.AssessmentResult
import com.fish.keXieOnlineJudge.mapper.example.AssessmentResultMapper
import com.fish.keXieOnlineJudge.service.example.AssessmentResultService
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AssessmentResultServiceImpl: ServiceImpl<AssessmentResultMapper, AssessmentResult>(), AssessmentResultService {
    @Transactional
    override fun addAssessmentResult(assessmentResult: AssessmentResult): Boolean {
        val i = mapper.insert(assessmentResult)
        return i > 0
    }

    /**
     * 通过存在数据库表中hash字段来查找测评结果
     *
     * 使用的是SHA1算法，直接用 = 判断
     *
     * 若存在hash值相同的代码，则返回测评的结果
     *
     */
    override fun matchHashByTopicId(topicId: Long, hash: String): AssessmentResult? {
        val hashList = mapper.getHashList(topicId)
        hashList.forEach {
            if (it.hash == hash)
                return it
        }
        return null
    }
}