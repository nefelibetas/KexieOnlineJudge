package com.fish.entity.pojo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_account")
data class Account(
    /**
     * 用户id
     */
    @Id(keyType = KeyType.Auto)
    var userId: String?,
    /**
     * 身份id
     */
    var roleId: Long?,
    /**
     * 昵称
     */
    var nickname: String?,
    /**
     * 头像
     */
    var avatar: String?,
    /**
     * 学号
     */
    var studentId: String?,
    /**
     * 姓名
     */
    var username: String?,
    /**
     * 密码
     */
    var password: String?,
    /**
     * 性别
     */
    var gender: String?,
    /**
     * 邮箱
     */
    var email: String?,
    /**
     * 专业
     */
    var specialty: String?,
    /**
     * QQ号
     */
    var qq: String?,
    /**
     * 博客地址
     */
    var blogAddress: String?,
    /**
     * GitHub地址
     */
    var githubAddress: String?,
    /**
     * 是否启用
     */
    var enabled: Boolean?,
    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var createTime: LocalDateTime?,
) : Serializable
