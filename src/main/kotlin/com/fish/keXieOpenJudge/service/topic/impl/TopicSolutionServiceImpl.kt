package com.fish.keXieOpenJudge.service.topic.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.topic.InsertTopicSolutionDTO
import com.fish.keXieOpenJudge.entity.pojo.topic.TopicSolution
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicSolutionTableDef.TOPIC_SOLUTION
import com.fish.keXieOpenJudge.entity.vo.PreviewTopicSolution
import com.fish.keXieOpenJudge.entity.vo.TopicSolutionVO
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.topic.TopicSolutionMapper
import com.fish.keXieOpenJudge.service.topic.TopicSolutionCommentService
import com.fish.keXieOpenJudge.service.topic.TopicSolutionService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TopicSolutionServiceImpl(val topicSolutionCommentService: TopicSolutionCommentService): ServiceImpl<TopicSolutionMapper, TopicSolution>(), TopicSolutionService {
    @Transactional
    override fun addSolution(solution: InsertTopicSolutionDTO): Result<*> {
        val i = mapper.addSolution(solution)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getSolution(solutionId: Long, pageNo: Int, pageSize: Int): Result<TopicSolutionVO> {
        if (Objects.isNull(solutionId))
            throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val wrapper = QueryWrapper.create()
            .select(TOPIC_SOLUTION.SOLUTION_ID, TOPIC_SOLUTION.TITLE, TOPIC_SOLUTION.SOLUTION_CONTENT)
            .from(TOPIC_SOLUTION)
            .where(TOPIC_SOLUTION.SOLUTION_ID.eq(solutionId)).and(TOPIC_SOLUTION.ENABLED.eq(true))
        val topicSolution = mapper.selectOneByQuery(wrapper)
        val topicSolutionVO = TopicSolutionVO()
        topicSolutionVO.solutionId = topicSolution.solutionId
        topicSolutionVO.solutionContent = topicSolution.solutionContent
        topicSolutionVO.title = topicSolution.title
        val data = topicSolutionCommentService.getFirstComment(solutionId, pageNo, pageSize)
        topicSolutionVO.comments = data.data
        if (!Objects.isNull(topicSolutionVO))
            return success(topicSolutionVO)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    override fun getAllSolutions(topicId: Long, pageNo: Int, pageSize: Int): Result<Page<PreviewTopicSolution>> {
        val wrapper = QueryWrapper.create()
            .select(TOPIC_SOLUTION.ALL_COLUMNS)
            .from(TOPIC_SOLUTION)
            .where(TOPIC_SOLUTION.TOPIC_ID.eq(topicId))
            .and(TOPIC_SOLUTION.ENABLED.eq(true))
        val solutions = mapper.paginateAs(Page.of(pageNo, pageSize), wrapper, PreviewTopicSolution::class.java)
        for ((index, previewTopicSolution) in solutions.records.withIndex()) {
            previewTopicSolution.likeNumber = topicSolutionCommentService.getLikeNumber(previewTopicSolution.solutionId!!)
            previewTopicSolution.commentNumber = topicSolutionCommentService.getCommentNumber(previewTopicSolution.solutionId)
        }
        return success(solutions)
    }

    @Transactional
    override fun changeStatus(topicId: Long, solutionId: Long, action: Boolean): Result<*> {
        if (action) {
            val wrapper = QueryWrapper.create().select(TOPIC_SOLUTION.ALL_COLUMNS).from(TOPIC_SOLUTION)
                .where(TOPIC_SOLUTION.TOPIC_ID.eq(topicId)).and(TOPIC_SOLUTION.PINED.eq(true)).and(TOPIC_SOLUTION.ENABLED.eq(true))
            val i = mapper.selectCountByQuery(wrapper)
            if (i.toInt() != 0)
                throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        }
        val update = UpdateChain.of(TopicSolution::class.java)
            .set(TopicSolution::enabled.column, action)
            .where(TopicSolution::solutionId eq solutionId)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun changePined(topicId: Long, solutionId: Long, action: Boolean): Result<*> {
        if (action) {
            val wrapper = QueryWrapper.create().select(TOPIC_SOLUTION.ALL_COLUMNS).from(TOPIC_SOLUTION)
                .where(TOPIC_SOLUTION.TOPIC_ID.eq(topicId)).and(TOPIC_SOLUTION.PINED.eq(true)).and(TOPIC_SOLUTION.ENABLED.eq(true))
            val i = mapper.selectCountByQuery(wrapper)
            if (i.toInt() != 0)
                throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        }
        val update = UpdateChain.of(TopicSolution::class.java)
            .set(TopicSolution::pined.column, action)
            .where(TopicSolution::solutionId eq solutionId)
            .and(TopicSolution::topicId eq topicId)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
}
