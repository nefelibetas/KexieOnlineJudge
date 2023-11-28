package com.fish.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginAccountDTO {
    @NotBlank(message = "邮箱不能为空")
    String email;
    @NotBlank(message = "密码不能为空")
    String password;
}
