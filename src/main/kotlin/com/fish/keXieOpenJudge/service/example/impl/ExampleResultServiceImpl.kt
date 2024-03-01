package com.fish.keXieOpenJudge.service.example.impl

import com.fish.keXieOpenJudge.entity.pojo.example.ExampleResult
import com.fish.keXieOpenJudge.mapper.example.ExampleResultMapper
import com.fish.keXieOpenJudge.service.example.ExampleResultService
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExampleResultServiceImpl: ServiceImpl<ExampleResultMapper, ExampleResult>(), ExampleResultService {
    @Transactional
    override fun addExampleResult(exampleResultList: List<ExampleResult>): Boolean {
        val i = mapper.insertBatch(exampleResultList)
        return i == exampleResultList.size
    }
}