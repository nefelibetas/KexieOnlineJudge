package com.fish.keXieOpenJudge.entity.dto.topic

import com.fish.keXieOpenJudge.entity.dto.example.InsertExampleDTO
import jakarta.validation.constraints.*
import java.io.Serializable

class InsertTopicDTO: Serializable {
    val topicId: Long? = null
    @NotBlank(message = "上传人id不能为空")
    val uploadUserId: String? = null
    @NotBlank(message = "题目未填写")
    @Size(min = 2, max = 32, message = "题目要求在2~32字内")
    val title: String? = null
    @NotBlank(message = "题面未填写")
    @Size(min = 2, max = 1000, message = "题面要求在2~1000字内")
    val content: String? = null
    @NotBlank(message = "难度不能为空")
    @Pattern(regexp = "^([低中高])$", message = "只能在低、中、高中选择")
    val difficulty: String? = null
    @NotNull(message = "最大内存未填写")
    val limitedMemory: Long? = null
    @NotNull(message = "最大时间未填写")
    val limitedTime: Long? = null
    @NotBlank(message = "输入描述未填写")
    @Size(min = 2, max = 1000, message = "输入描述要求在2~1000字内")
    val inputDescribe: String? = null
    @NotBlank(message = "输出描述未填写")
    @Size(min = 2, max = 1000, message = "输出描述要求在2~1000字内")
    val outputDescribe:  String? = null
    val precautions: String? = null
    val from: String? = null
    @NotEmpty(message = "题目样例不能为空")
    val examples: ArrayList<InsertExampleDTO>? = null
    @NotEmpty(message = "至少选择一个标签")
    val labelsId: ArrayList<Long>? = null
}