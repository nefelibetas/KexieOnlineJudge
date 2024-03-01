package com.fish.kexieOnlineJudge.entity.dto.example

import jakarta.validation.constraints.NotBlank

class InsertExampleDTO {
    var topicId: Long? = null
    @NotBlank(message = "输入样例不能为空")
    val input: String? = null
    @NotBlank(message = "输出样例不能为空")
    val output: String? = null
    /**
     * 是否展示
     */
    val showed: Boolean = true
    /**
     * 是否参与测评
     */
    val assessed: Boolean = true
}
