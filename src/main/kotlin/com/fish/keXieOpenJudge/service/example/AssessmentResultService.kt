package com.fish.keXieOpenJudge.service.example

import com.fish.keXieOpenJudge.entity.pojo.example.AssessmentResult
import com.mybatisflex.core.service.IService

interface AssessmentResultService: IService<AssessmentResult> {
    fun addAssessmentResult(assessmentResult: AssessmentResult): Boolean
    fun matchHashByTopicId(topicId: Long, hash: String): AssessmentResult?
}