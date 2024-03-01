package com.fish.kexieOnlineJudge.service.example

import com.fish.kexieOnlineJudge.entity.pojo.example.AssessmentResult
import com.mybatisflex.core.service.IService

interface AssessmentResultService: IService<AssessmentResult> {
    fun addAssessmentResult(assessmentResult: AssessmentResult): Boolean
    fun matchHashByTopicId(topicId: Long, hash: String): AssessmentResult?
}