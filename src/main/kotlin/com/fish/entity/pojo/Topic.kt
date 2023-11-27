package com.fish.entity.pojo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_topic")
data class Topic(
    @Id(keyType = KeyType.Auto)
    var topicId: Long?,
    var uploadUserId: @NotBlank(message = "上传人id不能为空") String?,
    var title: @NotBlank(message = "题目未填写") @Size(min = 2, max = 32, message = "题目要求在2~32字内") String?,

    /**
     * 题面
     */
    var content: @NotBlank(message = "题面未填写") @Size(
        min = 2,
        max = 1000,
        message = "题面要求在2~1000字内"
    ) String?,
    var difficulty: @Pattern(regexp = "^([低中高])$", message = "只能在低、中、高中选择") String?,

    /**
     * 限制内存
     */
    var limitedMemory: @NotNull(message = "最大内存未填写") Long?,

    /**
     * 限制时间
     */
    var limitedTime: @NotNull(message = "最大时间未填写") Long?,

    /**
     * 输入描述
     */
    var inputDescribe: @NotBlank(message = "输入描述未填写") @Size(
        min = 2,
        max = 1000,
        message = "输入描述要求在2~1000字内"
    ) String?,

    /**
     * 输出描述
     */
    var outputDescribe: @NotBlank(message = "输出描述未填写") @Size(
        min = 2,
        max = 1000,
        message = "输出描述要求在2~1000字内"
    ) String?,

    /**
     * 是否开启题解
     */
    var enabledSolution: Boolean?,

    /**
     * 题目创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var createTime: LocalDateTime?,

    /**
     * 题目更新时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var updateTime: LocalDateTime?,

    /**
     * 注意事项
     */
    var precautions: String?,
    var from: String?,
    var enabled: Boolean?,
) : Serializable