package com.fish.keXieOpenJudge.entity.dto.topic

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

class InsertTopicSolutionCommentDTO: Serializable {
    @NotBlank(message = "用户id不能为空")
    val userId: String? = null
    @NotNull(message = "题解id不能为空")
    val solutionId: Long? = null
    val parentId: Long? = null
    @NotBlank(message = "评论内容不能为空")
    val commentContent: String? = null
}