package com.fish.keXieOnlineJudge.entity.dto.account

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.io.Serializable

class PasswordDTO: Serializable {
    @NotBlank(message = "旧密码不能为空")
    val oldPwd: String? = null
    @NotBlank(message = "新密码不能为空")
    @Size(min = 8, max = 16, message = "密码最少8位,最多16位")
    val newPwd: String? = null
}