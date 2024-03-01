package com.fish.keXieOpenJudge.entity.dto.topic

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable

class InsertTopicSolutionDTO: Serializable {
    @NotNull(message = "题目id不能为空")
    val topicId: Long? = null
    @NotNull(message = "标题不能为空")
    @Size(min = 1, max = 10, message = "标题限制：1~40个汉字")
        val title: String? = null
    @NotNull(message = "内容不能为空")
    @Size(min = 1, max = 21500, message = "内容限制：1~21500个汉字")
    val content: String? = null
}