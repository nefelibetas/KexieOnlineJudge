package com.fish.entity.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.io.Serializable

class InsertColumnDTO: Serializable {
    var columnId: Long? = null
    @NotBlank(message = "栏目名未填写")
    @Size(min = 1, max = 32, message = "栏目名要求在1~32字符以内")
    val columnName:  String? = null
    @NotBlank(message = "栏目描述未填写")
    @Size(min = 1, max = 100, message = "栏目描述要求在1~100字符以内")
    val columnDescribe: String? = null
    val topicIds: ArrayList<Long>? = null
}