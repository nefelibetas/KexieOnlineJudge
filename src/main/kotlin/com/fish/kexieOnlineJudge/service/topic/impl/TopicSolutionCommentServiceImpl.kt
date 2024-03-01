package com.fish.kexieOnlineJudge.service.topic.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.topic.InsertTopicSolutionCommentDTO
import com.fish.kexieOnlineJudge.entity.pojo.account.table.AccountTableDef.ACCOUNT
import com.fish.kexieOnlineJudge.entity.pojo.topic.TopicSolutionComment
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.LikeTableDef.LIKE
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.TopicSolutionCommentTableDef.TOPIC_SOLUTION_COMMENT
import com.fish.kexieOnlineJudge.entity.vo.FirstCommentVO
import com.fish.kexieOnlineJudge.entity.vo.SecondCommentVO
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.topic.LikeMapper
import com.fish.kexieOnlineJudge.mapper.topic.TopicSolutionCommentMapper
import com.fish.kexieOnlineJudge.service.topic.TopicSolutionCommentService
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
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

    override fun getCommentLikeNumber(commentId: Long): Int {
        val wrapper = QueryWrapper.create()
            .select(LIKE.ALL_COLUMNS).from(LIKE)
            .where(LIKE.COMMENT_ID.eq(commentId))
            .and(LIKE.SOLUTION_ID.isNull)
        return likeMapper.selectCountByQuery(wrapper).toInt()
    }

    override fun getSolutionLikeNumber(solutionId: Long): Int {
        val wrapper = QueryWrapper.create()
            .select(LIKE.ALL_COLUMNS).from(LIKE)
            .where(LIKE.SOLUTION_ID.eq(solutionId))
            .and(LIKE.COMMENT_ID.isNull)
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
            it.secondNumber = this.getSecondCommentNumber(it.commentId!!)
            it.likeNumber = this.getCommentLikeNumber(it.commentId)
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
        val secondCommentVO = mapper.paginateAs(Page.of(pageNo, pageSize), wrapper, SecondCommentVO::class.java)
        secondCommentVO.records.forEach {
            it.likeNumber = this.getCommentLikeNumber(it.commentId!!)
        }
        return success(secondCommentVO)
    }
}