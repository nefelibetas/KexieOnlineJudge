package com.fish.kexieOnlineJudge.service.example.impl

import com.fish.kexieOnlineJudge.entity.pojo.example.ExampleResult
import com.fish.kexieOnlineJudge.mapper.example.ExampleResultMapper
import com.fish.kexieOnlineJudge.service.example.ExampleResultService
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