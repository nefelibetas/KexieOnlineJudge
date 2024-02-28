package com.fish.keXieOpenJudge.network

import com.fish.keXieOpenJudge.common.Result
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping

@Component
@FeignClient(name = "AssessmentService",url = "http://localhost:8080")
interface AssessmentFeign {
    @PostMapping("")
    fun doAssessment(): Result<*>
}