package com.fish.entity.dto

import jakarta.validation.constraints.NotBlank
import java.io.Serializable

data class LoginAccountDTO(
    val email: @NotBlank(message = "邮箱不能为空") String?,
    val password: @NotBlank(message = "密码不能为空") String?
) : Serializable {
    constructor() : this(null, null)
}
