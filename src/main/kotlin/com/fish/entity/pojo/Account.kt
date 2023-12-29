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
open class Account(
    userId: String?,
    roleId: Long?,
    nickname: String?,
    avatar: String?,
    studentId: String?,
    username: String?,
    password: String?,
    gender: String?,
    email: String?,
    specialty: String?,
    qq: String?,
    blogAddress: String?,
    githubAddress: String?,
    enabled: Boolean?,
    createTime: LocalDateTime?
): Serializable {
    /**
     * 用户id
     */
    @Id(keyType = KeyType.Auto)
    val userId: String? = null
    /**
     * 身份id
     */
    val roleId: Long? = null
    /**
     * 昵称
     */
    val nickname: String? = null
    /**
     * 头像
     */
    val avatar: String? = null
    /**
     * 学号
     */
    val studentId: String? = null
    /**
     * 姓名
     */
    val username: String? = null
    /**
     * 密码
     */
    val password: String? = null
    /**
     * 性别
     */
    val gender: String? = null
    /**
     * 邮箱
     */
    val email: String? = null
    /**
     * 专业
     */
    val specialty: String? = null
    /**
     * QQ号
     */
    val qq: String? = null
    /**
     * 博客地址
     */
    val blogAddress: String? = null
    /**
     * GitHub地址
     */
    val githubAddress: String? = null
    /**
     * 是否启用
     */
    val enabled: Boolean? = null
    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val createTime: LocalDateTime? = null
}
