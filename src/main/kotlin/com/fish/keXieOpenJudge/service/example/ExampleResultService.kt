package com.fish.keXieOpenJudge.service.example

import com.fish.keXieOpenJudge.entity.pojo.example.ExampleResult
import com.mybatisflex.core.service.IService

interface ExampleResultService: IService<ExampleResult> {
    fun addExampleResult(exampleResultList: List<ExampleResult>): Boolean
}