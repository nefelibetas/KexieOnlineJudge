package com.fish.entity.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "oj_account")
public class Account implements Serializable {
    /**
     * 用户id
     */
    @Id(keyType = KeyType.Auto)
    private String userId;
    /**
     * 身份id
     */
    private Long roleId;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @Size(min = 1, max = 32, message = "1~32字符")
    private String nickname;
    /**
     * 头像
     */
    @Size(max = 128, message = "最多128位")
    private String avatar;
    /**
     * 学号
     */
    @Size(max = 20, message = "请正确输入学号")
    private String studentId;
    /**
     * 姓名
     */
    @Size(min = 1, max = 32, message = "1~32字符")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 16, message = "密码最少8位,最多16位")
    private String password;
    /**
     * 性别
     */
    @Pattern(regexp = "^(男|女|保密)$", message = "只能从选项中选择")
    private String gender;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Size(min = 1, max = 32, message = "1~32字符")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 专业
     */
    @Pattern(regexp = "^(计算机科学与技术|软件工程|物联网工程|信息安全|智能科学与技术|网络空间安全)$", message = "只能从选项中选择")
    private String specialty;
    /**
     * QQ号
     */
    @Size(max = 20, message = "请正确输入QQ号")
    private String qq;
    /**
     * 博客地址
     */
    @Size(max = 128, message = "最多128位")
    private String blogAddress;
    /**
     * GitHub地址
     */
    @Size(max = 128, message = "最多128位")
    private String githubAddress;
    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
}
