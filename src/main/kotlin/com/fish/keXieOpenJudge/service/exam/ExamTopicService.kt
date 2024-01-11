package com.fish.keXieOpenJudge.service.exam

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.pojo.exam.ExamTopic
import com.mybatisflex.core.service.IService

interface ExamTopicService: IService<ExamTopic> {
    fun addTopicsToExam(examId: Long, examTopics: ArrayList<ExamTopic>): Result<*>
}