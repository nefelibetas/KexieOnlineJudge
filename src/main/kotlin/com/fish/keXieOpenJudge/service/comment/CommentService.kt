package com.fish.keXieOpenJudge.service.comment

import com.fish.keXieOpenJudge.entity.pojo.topic.TopicSolutionComment
import com.mybatisflex.core.service.IService

interface CommentService: IService<TopicSolutionComment> {
    fun getCommentNumber(solutionId: Long): Int
    fun getLikeNumber(solutionId: Long): Int
}