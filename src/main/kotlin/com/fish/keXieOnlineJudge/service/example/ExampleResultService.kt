package com.fish.keXieOnlineJudge.service.example

import com.fish.keXieOnlineJudge.entity.pojo.example.ExampleResult
import com.mybatisflex.core.service.IService

interface ExampleResultService: IService<ExampleResult> {
    fun addExampleResult(exampleResultList: List<ExampleResult>): Boolean
}