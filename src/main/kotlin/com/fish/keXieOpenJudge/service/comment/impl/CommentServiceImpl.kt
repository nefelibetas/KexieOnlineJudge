package com.fish.keXieOpenJudge.service.comment.impl

import com.fish.keXieOpenJudge.entity.pojo.topic.TopicSolutionComment
import com.fish.keXieOpenJudge.entity.pojo.topic.table.LikeTableDef.LIKE
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicSolutionCommentTableDef.TOPIC_SOLUTION_COMMENT
import com.fish.keXieOpenJudge.mapper.topic.LikeMapper
import com.fish.keXieOpenJudge.mapper.topic.TopicSolutionsCommentsMapper
import com.fish.keXieOpenJudge.service.comment.CommentService
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(val likeMapper: LikeMapper): ServiceImpl<TopicSolutionsCommentsMapper, TopicSolutionComment>(), CommentService {
    override fun getCommentNumber(solutionId: Long): Int {
        val wrapper = QueryWrapper.create()
            .select(TOPIC_SOLUTION_COMMENT.ALL_COLUMNS).from(TOPIC_SOLUTION_COMMENT)
            .where(TOPIC_SOLUTION_COMMENT.SOLUTION_ID.eq(solutionId))
            .and(TOPIC_SOLUTION_COMMENT.ENABLED.eq(true))
        return mapper.selectCountByQuery(wrapper).toInt()
    }

    override fun getLikeNumber(solutionId: Long): Int {
        val wrapper = QueryWrapper.create()
            .select(LIKE.ALL_COLUMNS).from(LIKE)
            .where(LIKE.SOLUTION_ID.eq(solutionId))
            .and(LIKE.COMMENT_ID.eq(null))
        return likeMapper.selectCountByQuery(wrapper).toInt()
    }
}