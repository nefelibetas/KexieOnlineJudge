package com.fish.kexieOnlineJudge.service.example.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.example.InsertExampleDTO
import com.fish.kexieOnlineJudge.entity.dto.example.UpdateExampleDTO
import com.fish.kexieOnlineJudge.entity.pojo.example.Example
import com.fish.kexieOnlineJudge.entity.vo.ExampleVO
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.example.ExampleMapper
import com.fish.kexieOnlineJudge.service.example.ExampleService
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
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
    override fun getExamplesById(topicId: Long): List<Example> {
        return mapper.getExampleById(topicId)
    }
    override fun getExampleVoByResultId(resultId: Long): ArrayList<ExampleVO> {
        return mapper.getExampleVoByResultId(resultId)
    }
}