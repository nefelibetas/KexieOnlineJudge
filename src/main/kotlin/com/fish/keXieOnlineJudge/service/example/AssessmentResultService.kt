package com.fish.keXieOnlineJudge.service.example

import com.fish.keXieOnlineJudge.entity.pojo.example.AssessmentResult
import com.mybatisflex.core.service.IService

interface AssessmentResultService: IService<AssessmentResult> {
    fun addAssessmentResult(assessmentResult: AssessmentResult): Boolean
    fun matchHashByTopicId(topicId: Long, hash: String): AssessmentResult?
}