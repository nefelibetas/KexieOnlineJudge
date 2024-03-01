package com.fish.kexieOnlineJudge.entity.dto.account

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.io.Serializable

class RegisterAccountDTO: Serializable {
    @NotBlank(message = "昵称不能为空")
    @Size(min = 1, max = 32, message = "1~32字符")
    val nickname: String? = null
    @Size(max = 128, message = "最多128位")
    val avatar: String? = null
    @Size(max = 20, message = "请正确输入学号")
    val studentId: String? = null
    @Size(min = 1, max = 32, message = "1~32字符")
    val username: String? = null
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 16, message = "密码最少8位,最多16位")
    var password: String? = null
    @Pattern(regexp = "^(男|女|保密)$", message = "只能从选项中选择")
    val gender: String? = null
    @NotBlank(message = "邮箱不能为空")
    @Size(min = 1, max = 32, message = "1~32字符")
    @Email(message = "邮箱格式不正确")
    val email: String? = null
    @Pattern(regexp = "^(计算机科学与技术|软件工程|物联网工程|信息安全|智能科学与技术|网络空间安全)$", message = "只能从选项中选择")
    val specialty: String? = null
    @Size(max = 20, message = "请正确输入QQ号")
    val qq: String? = null
    @Size(max = 128, message = "最多128位")
    val blogAddress: String? = null
    @Size(max = 128, message = "最多128位")
    val githubAddress: String? = null
}