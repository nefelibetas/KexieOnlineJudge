package com.fish.keXieOpenJudge.service.exam.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.pojo.exam.ExamTopic
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.example.ExamTopicMapper
import com.fish.keXieOpenJudge.service.exam.ExamTopicService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ExamTopicServiceImpl: ServiceImpl<ExamTopicMapper, ExamTopic>(), ExamTopicService {
    @Transactional
    override fun addTopicsToExam(examId: Long, examTopics: ArrayList<ExamTopic>): Result<*> {
        for (examTopic in examTopics) {
            examTopic.examId = examId
        }
        val i = mapper.insertBatch(examTopics as List<ExamTopic>)
        if (i == examTopics.size)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
}