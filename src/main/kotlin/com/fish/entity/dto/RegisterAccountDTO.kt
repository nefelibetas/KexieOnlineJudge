package com.fish.entity.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.io.Serializable

data class RegisterAccountDTO(
    var nickname: @NotBlank(message = "昵称不能为空") @Size(min = 1, max = 32, message = "1~32字符") String? = null,
    var avatar: @Size(max = 128, message = "最多128位") String? = null,
    var studentId: @Size(max = 20, message = "请正确输入学号") String? = null,
    var username: @Size(min = 1, max = 32, message = "1~32字符") String? = null,
    var password: @NotBlank(message = "密码不能为空") @Size(
        min = 8,
        max = 16,
        message = "密码最少8位,最多16位"
    ) String? = null,
    var gender: @Pattern(regexp = "^(男|女|保密)$", message = "只能从选项中选择") String? = null,
    var email: @NotBlank(message = "邮箱不能为空") @Size(
        min = 1,
        max = 32,
        message = "1~32字符"
    ) @Email(message = "邮箱格式不正确") String? = null,
    var specialty: @Pattern(
        regexp = "^(计算机科学与技术|软件工程|物联网工程|信息安全|智能科学与技术|网络空间安全)$",
        message = "只能从选项中选择"
    ) String? = null,
    var qq: @Size(max = 20, message = "请正确输入QQ号") String? = null,
    var blogAddress: @Size(max = 128, message = "最多128位") String? = null,
    var githubAddress: @Size(max = 128, message = "最多128位") String? = null
) : Serializable
