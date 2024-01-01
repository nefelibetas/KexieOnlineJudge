package com.fish.keXieOpenJudge.entity.dto.column

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable

class UpdateColumnDTO: Serializable {
    @NotNull(message = "栏目id不能为空")
    val columnId: String? = null
    @Size(min = 1, max = 32, message = "栏目名要求在1~32字符以内")
    val columnName:  String? = null
    @Size(min = 1, max = 100, message = "栏目描述要求在1~100字符以内")
    val columnDescribe: String? = null
}