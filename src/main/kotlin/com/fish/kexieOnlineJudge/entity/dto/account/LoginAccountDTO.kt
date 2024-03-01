package com.fish.kexieOnlineJudge.entity.dto.account

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.io.Serializable

class LoginAccountDTO : Serializable {
    @NotBlank(message = "邮箱不能为空") @Size(min = 1, max = 32, message = "1~32字符")
    @Email(message = "邮箱格式不正确")
    val email: String? = null
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 16, message = "密码最少8位,最多16位")
    val password: String? = null
}
