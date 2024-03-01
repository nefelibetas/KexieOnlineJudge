package com.fish.keXieOnlineJudge.service.exam

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.pojo.exam.ExamTopic
import com.mybatisflex.core.service.IService

interface ExamTopicService: IService<ExamTopic> {
    fun addTopicsToExam(examId: Long, examTopics: ArrayList<ExamTopic>): Result<*>
}