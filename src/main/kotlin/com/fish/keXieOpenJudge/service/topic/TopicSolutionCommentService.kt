package com.fish.keXieOpenJudge.service.topic

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.topic.InsertTopicSolutionCommentDTO
import com.fish.keXieOpenJudge.entity.pojo.topic.TopicSolutionComment
import com.fish.keXieOpenJudge.entity.vo.FirstCommentVO
import com.fish.keXieOpenJudge.entity.vo.SecondCommentVO
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