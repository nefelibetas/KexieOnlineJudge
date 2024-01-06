package com.fish.keXieOpenJudge.service.topic.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.topic.InsertTopicSolutionCommentDTO
import com.fish.keXieOpenJudge.entity.pojo.account.table.AccountTableDef.ACCOUNT
import com.fish.keXieOpenJudge.entity.pojo.topic.TopicSolutionComment
import com.fish.keXieOpenJudge.entity.pojo.topic.table.LikeTableDef
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicSolutionCommentTableDef.TOPIC_SOLUTION_COMMENT
import com.fish.keXieOpenJudge.entity.vo.FirstCommentVO
import com.fish.keXieOpenJudge.entity.vo.SecondCommentVO
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.topic.LikeMapper
import com.fish.keXieOpenJudge.mapper.topic.TopicSolutionCommentMapper
import com.fish.keXieOpenJudge.service.topic.TopicSolutionCommentService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicSolutionCommentServiceImpl(val likeMapper: LikeMapper): ServiceImpl<TopicSolutionCommentMapper, TopicSolutionComment>(), TopicSolutionCommentService{
    @Transactional
    override fun addComment(insertTopicSolutionCommentDTO: InsertTopicSolutionCommentDTO): Result<*> {
        val i = mapper.addComment(insertTopicSolutionCommentDTO)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun deleteComment(commentId: Long): Result<*> {
        val update = UpdateChain.of(TopicSolutionComment::class.java)
            .set(TopicSolutionComment::enabled.column, false)
            .where(TopicSolutionComment::commentId eq commentId)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getCommentNumber(solutionId: Long): Int {
        val wrapper = QueryWrapper.create()
            .select(TOPIC_SOLUTION_COMMENT.ALL_COLUMNS).from(TOPIC_SOLUTION_COMMENT)
            .where(TOPIC_SOLUTION_COMMENT.SOLUTION_ID.eq(solutionId))
            .and(TOPIC_SOLUTION_COMMENT.ENABLED.eq(true))
        return mapper.selectCountByQuery(wrapper).toInt()
    }

    override fun getLikeNumber(solutionId: Long): Int {
        val wrapper = QueryWrapper.create()
            .select(LikeTableDef.LIKE.ALL_COLUMNS).from(LikeTableDef.LIKE)
            .where(LikeTableDef.LIKE.SOLUTION_ID.eq(solutionId))
            .and(LikeTableDef.LIKE.COMMENT_ID.isNull)
        return likeMapper.selectCountByQuery(wrapper).toInt()
    }

    override fun getFirstComment(solutionId: Long, pageNo: Int, pageSize: Int): Result<Page<FirstCommentVO>> {
        val wrapper = QueryWrapper.create()
            .select(TOPIC_SOLUTION_COMMENT.ALL_COLUMNS, ACCOUNT.ALL_COLUMNS).from(TOPIC_SOLUTION_COMMENT)
            .leftJoin<QueryWrapper>(ACCOUNT).on(ACCOUNT.USER_ID.eq(TOPIC_SOLUTION_COMMENT.USER_ID))
            .where(TOPIC_SOLUTION_COMMENT.ENABLED.eq(true))
            .and(TOPIC_SOLUTION_COMMENT.PARENT_ID.isNull)
            .and(TOPIC_SOLUTION_COMMENT.SOLUTION_ID.eq(solutionId))
        val firstCommentVO = mapper.paginateAs(Page.of(pageNo, pageSize), wrapper, FirstCommentVO::class.java)
        firstCommentVO.records.forEach {
            it.number = this.getSecondCommentNumber(it.commentId!!)
        }
        return success(firstCommentVO)
    }

    override fun getSecondCommentNumber(commentId: Long): Int {
        val wrapper = QueryWrapper.create()
            .select(TOPIC_SOLUTION_COMMENT.ALL_COLUMNS)
            .from(TOPIC_SOLUTION_COMMENT)
            .where(TOPIC_SOLUTION_COMMENT.ENABLED.eq(true))
            .and(TOPIC_SOLUTION_COMMENT.PARENT_ID.eq(commentId))
        return count(wrapper).toInt()
    }

    override fun getSecondComment(commentId: Long, pageNo: Int, pageSize: Int): Result<Page<SecondCommentVO>> {
        val wrapper = QueryWrapper.create()
            .select(TOPIC_SOLUTION_COMMENT.ALL_COLUMNS, ACCOUNT.ALL_COLUMNS).from(TOPIC_SOLUTION_COMMENT)
            .leftJoin<QueryWrapper>(ACCOUNT).on(TOPIC_SOLUTION_COMMENT.USER_ID.eq(ACCOUNT.USER_ID))
            .where(TOPIC_SOLUTION_COMMENT.ENABLED.eq(true))
            .and(TOPIC_SOLUTION_COMMENT.PARENT_ID.eq(commentId))
        val secondCommentVOPage = mapper.paginateAs(Page.of(pageNo, pageSize), wrapper, SecondCommentVO::class.java)
        return success(secondCommentVOPage)
    }
}