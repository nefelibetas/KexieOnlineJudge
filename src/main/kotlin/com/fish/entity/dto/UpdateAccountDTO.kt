package com.fish.entity.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.io.Serializable

data class UpdateAccountDTO(
    var userId:  String?,
    val nickname: @Size(min = 1, max = 32, message = "1~32字符") String?,
    val avatar: @Size(max = 128, message = "最多128位") String?,
    val gender: @Pattern(regexp = "^(男|女|保密)$", message = "只能从选项中选择") String?,
    val email: @Size(
        min = 1,
        max = 32,
        message = "1~32字符"
    ) @Email(message = "邮箱格式不正确") String?,
    val qq: @Size(max = 20, message = "请正确输入QQ号") String?,
    val blogAddress: @Size(max = 128, message = "最多128位") String?,
    val githubAddress: @Size(max = 128, message = "最多128位") String?
): Serializable
