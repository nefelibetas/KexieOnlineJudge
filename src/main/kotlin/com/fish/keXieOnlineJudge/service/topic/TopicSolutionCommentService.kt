package com.fish.keXieOnlineJudge.service.topic

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.dto.topic.InsertTopicSolutionCommentDTO
import com.fish.keXieOnlineJudge.entity.pojo.topic.TopicSolutionComment
import com.fish.keXieOnlineJudge.entity.vo.FirstCommentVO
import com.fish.keXieOnlineJudge.entity.vo.SecondCommentVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface TopicSolutionCommentService: IService<TopicSolutionComment> {
    fun addComment(insertTopicSolutionCommentDTO: InsertTopicSolutionCommentDTO): Result<*>
    fun deleteComment(commentId: Long): Result<*>
    fun getCommentNumber(solutionId: Long): Int
    fun getSolutionLikeNumber(solutionId: Long): Int
    fun getCommentLikeNumber(commentId: Long): Int
    fun getFirstComment(solutionId: Long, pageNo: Int, pageSize: Int): Result<Page<FirstCommentVO>>
    fun getSecondCommentNumber(commentId: Long): Int
    fun getSecondComment(commentId: Long, pageNo: Int, pageSize: Int): Result<Page<SecondCommentVO>>
}