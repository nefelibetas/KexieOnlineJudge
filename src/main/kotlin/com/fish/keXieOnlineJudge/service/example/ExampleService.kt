package com.fish.keXieOnlineJudge.service.example

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.dto.example.InsertExampleDTO
import com.fish.keXieOnlineJudge.entity.dto.example.UpdateExampleDTO
import com.fish.keXieOnlineJudge.entity.pojo.example.Example
import com.fish.keXieOnlineJudge.entity.vo.ExampleVO
import com.mybatisflex.core.service.IService

interface ExampleService: IService<Example>{
    fun addExampleBatch(examples: ArrayList<InsertExampleDTO>): Result<*>
    fun updateExample(updateExampleDTO: UpdateExampleDTO): Result<*>
    fun getExamplesById(topicId: Long): List<Example>
    fun getExampleVoByResultId(resultId: Long): ArrayList<ExampleVO>
}