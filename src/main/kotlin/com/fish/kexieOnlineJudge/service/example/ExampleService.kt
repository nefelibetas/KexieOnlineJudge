package com.fish.kexieOnlineJudge.service.example

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.example.InsertExampleDTO
import com.fish.kexieOnlineJudge.entity.dto.example.UpdateExampleDTO
import com.fish.kexieOnlineJudge.entity.pojo.example.Example
import com.fish.kexieOnlineJudge.entity.vo.ExampleVO
import com.mybatisflex.core.service.IService

interface ExampleService: IService<Example>{
    fun addExampleBatch(examples: ArrayList<InsertExampleDTO>): Result<*>
    fun updateExample(updateExampleDTO: UpdateExampleDTO): Result<*>
    fun getExamplesById(topicId: Long): List<Example>
    fun getExampleVoByResultId(resultId: Long): ArrayList<ExampleVO>
}