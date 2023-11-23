package com.fish.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAccountDTO {
    @NotBlank(message = "昵称不能为空")
    @Size(min = 1, max = 32, message = "1~32字符")
    private String nickname;
    @Size(max = 128, message = "最多128位")
    private String avatar;
    @Size(max = 20, message = "请正确输入学号")
    private String studentId;
    @Size(min = 1, max = 32, message = "1~32字符")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 16, message = "密码最少8位,最多16位")
    private String password;
    @Pattern(regexp = "^(男|女|保密)$", message = "只能从选项中选择")
    private String gender;
    @NotBlank(message = "邮箱不能为空")
    @Size(min = 1, max = 32, message = "1~32字符")
    @Email(message = "邮箱格式不正确")
    private String email;
    @Pattern(regexp = "^(计算机科学与技术|软件工程|物联网工程|信息安全|智能科学与技术|网络空间安全)$", message = "只能从选项中选择")
    private String specialty;
    @Size(max = 20, message = "请正确输入QQ号")
    private String qq;
    @Size(max = 128, message = "最多128位")
    private String blogAddress;
    @Size(max = 128, message = "最多128位")
    private String githubAddress;
}
