package com.fish.keXieOpenJudge.service.topic.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.solution.InsertSolutionDTO
import com.fish.keXieOpenJudge.entity.pojo.topic.TopicSolution
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicSolutionTableDef.TOPIC_SOLUTION
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.topic.TopicSolutionMapper
import com.fish.keXieOpenJudge.service.topic.TopicSolutionService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TopicSolutionServiceImpl : ServiceImpl<TopicSolutionMapper, TopicSolution>(), TopicSolutionService {
    @Transactional
    override fun addSolution(solution: InsertSolutionDTO): Result<*> {
        val i = mapper.addSolution(solution)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getSolution(solutionId: Long): Result<TopicSolution> {
        val wrapper = QueryWrapper.create()
            .select().from(TOPIC_SOLUTION)
            .where(TOPIC_SOLUTION.SOLUTION_ID.eq(solutionId))
            .and(TOPIC_SOLUTION.ENABLED.eq(true))
        val solution = mapper.selectOneByQuery(wrapper)
        if (!Objects.isNull(solution))
            return success(solution)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    override fun getAllSolutions(topicId: Long): Result<ArrayList<TopicSolution>> {
        val wrapper = QueryWrapper.create()
            .select(TOPIC_SOLUTION.ALL_COLUMNS).from(TOPIC_SOLUTION)
            .where(TOPIC_SOLUTION.TOPIC_ID.eq(topicId))
            .and(TOPIC_SOLUTION.ENABLED.eq(true))
        val solutions = mapper.selectListByQuery(wrapper) as ArrayList<TopicSolution>
        if (solutions.size > 0)
            return success(solutions)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    @Transactional
    override fun setPined(topicId: Long, solutionId: Long): Result<*> {
        val topicSolutionArrayList = mapper.selectPined(topicId)
        if (topicSolutionArrayList.size == 1)
            throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR.code, ServiceExceptionEnum.OPERATE_ERROR.msg + ",已经存在置顶")
        val update = UpdateChain.of(TopicSolution::class.java)
            .set(TopicSolution::pined.column, true)
            .where(TopicSolution::solutionId eq solutionId)
            .and(TopicSolution::enabled eq true)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun disablePined(solutionId: Long): Result<*> {
        val update = UpdateChain.of(TopicSolution::class.java)
            .set(TopicSolution::pined.column, false)
            .where(TopicSolution::solutionId eq solutionId)
            .and(TopicSolution::enabled eq true)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun disableSolution(solutionId: Long): Result<*> {
        val update = UpdateChain.of(TopicSolution::class.java)
            .set(TopicSolution::enabled.column, false)
            .where(TopicSolution::solutionId eq solutionId)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun enableSolution(solutionId: Long): Result<*> {
        val update = UpdateChain.of(TopicSolution::class.java)
            .set(TopicSolution::enabled.column, true)
            .where(TopicSolution::solutionId eq solutionId)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
}
