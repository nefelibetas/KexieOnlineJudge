package com.fish.keXieOnlineJudge.service.exam.impl

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.pojo.exam.ExamTopic
import com.fish.keXieOnlineJudge.exception.ServiceException
import com.fish.keXieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.keXieOnlineJudge.mapper.example.ExamTopicMapper
import com.fish.keXieOnlineJudge.service.exam.ExamTopicService
import com.fish.keXieOnlineJudge.utils.ResultUtil.success
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