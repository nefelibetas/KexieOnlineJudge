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
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_topic")
open class Topic: Serializable {
    @Id(keyType = KeyType.Auto)
    val topicId: Long? = null
    val uploadUserId: String? = null
    val title: String? = null
    /**
     * 题面
     */
    val content: String? = null
    val difficulty: String? = null
    /**
     * 限制内存
     */
    val limitedMemory: Long? = null
    /**
     * 限制时间
     */
    val limitedTime: Long? = null
    /**
     * 输入描述
     */
    val inputDescribe: String? = null
    /**
     * 输出描述
     */
    val outputDescribe:  String? = null
    /**
     * 是否开启题解
     */
    val enabledSolution: Boolean? = null
    /**
     * 题目创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val createTime: LocalDateTime? = null
    /**
     * 题目更新时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val updateTime: LocalDateTime? = null
    /**
     * 注意事项
     */
    val precautions: String? = null
    val from: String? = null
    val enabled: Boolean? = null
}
