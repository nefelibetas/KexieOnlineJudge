package com.fish.keXieOpenJudge.service.example.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.example.InsertExampleDTO
import com.fish.keXieOpenJudge.entity.dto.example.UpdateExampleDTO
import com.fish.keXieOpenJudge.entity.pojo.example.Example
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.example.ExampleMapper
import com.fish.keXieOpenJudge.service.example.ExampleService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExampleServiceImpl: ServiceImpl<ExampleMapper, Example>(), ExampleService {
    @Transactional
    override fun addExampleBatch(examples: ArrayList<InsertExampleDTO>): Result<*> {
        val i = mapper.addExampleBatch(examples)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
    @Transactional
    override fun updateExample(updateExampleDTO: UpdateExampleDTO): Result<*> {
        val i = mapper.updateExample(updateExampleDTO)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
}