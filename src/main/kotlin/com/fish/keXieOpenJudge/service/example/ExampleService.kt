package com.fish.keXieOpenJudge.service.example

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.example.InsertExampleDTO
import com.fish.keXieOpenJudge.entity.dto.example.UpdateExampleDTO
import com.fish.keXieOpenJudge.entity.pojo.example.Example
import com.mybatisflex.core.service.IService

interface ExampleService: IService<Example>{
    fun addExampleBatch(examples: ArrayList<InsertExampleDTO>): Result<*>
    fun updateExample(updateExampleDTO: UpdateExampleDTO): Result<*>
}