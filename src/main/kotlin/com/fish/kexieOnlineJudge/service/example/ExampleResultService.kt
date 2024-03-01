package com.fish.kexieOnlineJudge.service.example

import com.fish.kexieOnlineJudge.entity.pojo.example.ExampleResult
import com.mybatisflex.core.service.IService

interface ExampleResultService: IService<ExampleResult> {
    fun addExampleResult(exampleResultList: List<ExampleResult>): Boolean
}