package com.fish.kexieOnlineJudge.service.exam

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.pojo.exam.ExamTopic
import com.mybatisflex.core.service.IService

interface ExamTopicService: IService<ExamTopic> {
    fun addTopicsToExam(examId: Long, examTopics: ArrayList<ExamTopic>): Result<*>
}