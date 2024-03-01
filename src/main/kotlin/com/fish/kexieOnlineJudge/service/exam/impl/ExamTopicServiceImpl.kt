package com.fish.kexieOnlineJudge.service.exam.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.pojo.exam.ExamTopic
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.example.ExamTopicMapper
import com.fish.kexieOnlineJudge.service.exam.ExamTopicService
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
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