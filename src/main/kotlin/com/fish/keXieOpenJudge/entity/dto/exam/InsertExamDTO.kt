package com.fish.keXieOpenJudge.entity.dto.exam

import com.fish.keXieOpenJudge.entity.pojo.exam.ExamTopic
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.io.Serializable

class InsertExamDTO: Serializable {
    var examId: Long? = null
    @NotBlank(message = "出卷人id不能为空")
    val hostId: String? = null
    @NotBlank(message = "试卷描述不能为空")
    val describe: String? = null
    @NotEmpty(message = "至少选择一道题目")
    val examTopics: ArrayList<ExamTopic>? = null
}