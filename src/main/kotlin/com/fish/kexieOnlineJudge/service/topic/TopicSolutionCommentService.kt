package com.fish.kexieOnlineJudge.service.topic

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.topic.InsertTopicSolutionCommentDTO
import com.fish.kexieOnlineJudge.entity.pojo.topic.TopicSolutionComment
import com.fish.kexieOnlineJudge.entity.vo.FirstCommentVO
import com.fish.kexieOnlineJudge.entity.vo.SecondCommentVO
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