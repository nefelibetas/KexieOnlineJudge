package com.fish.entity.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.io.Serializable

class UpdateAccountDTO: Serializable {
    var userId:  String? = null
    @Size(min = 1, max = 32, message = "1~32字符")
    val nickname: String? = null
    @Size(max = 128, message = "最多128位")
    val avatar: String? = null
    @Pattern(regexp = "^(男|女|保密)$", message = "只能从选项中选择")
    val gender: String? = null
    @Size(min = 1, max = 32, message = "1~32字符")
    @Email(message = "邮箱格式不正确")
    val email: String? = null
    @Size(max = 20, message = "请正确输入QQ号")
    val qq: String? = null
    @Size(max = 128, message = "最多128位")
    val blogAddress: String? = null
    @Size(max = 128, message = "最多128位")
    val githubAddress: String? = null
}
