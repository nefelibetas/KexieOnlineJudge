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
open class Topic(
    topicId: Long?,
    uploadUserId: String?,
    title: String?,
    content: String?,
    difficulty: String?,
    limitedMemory: Long?,
    limitedTime: Long?,
    inputDescribe: String?,
    outputDescribe: String?,
    enabledSolution: Boolean?,
    createTime: LocalDateTime?,
    updateTime: LocalDateTime?,
    precautions: String?,
    from: String?,
    enabled: Boolean?
): Serializable {
    @Id(keyType = KeyType.Auto)
    val topicId: Long? = null
    val uploadUserId: @NotBlank(message = "上传人id不能为空") String? = null
    val title: @NotBlank(message = "题目未填写") @Size(min = 2, max = 32, message = "题目要求在2~32字内") String? = null
    /**
     * 题面
     */
    val content: @NotBlank(message = "题面未填写") @Size(
        min = 2,
        max = 1000,
        message = "题面要求在2~1000字内"
    ) String? = null
    val difficulty: @Pattern(regexp = "^([低中高])$", message = "只能在低、中、高中选择") String? = null
    /**
     * 限制内存
     */
    val limitedMemory: @NotNull(message = "最大内存未填写") Long? = null
    /**
     * 限制时间
     */
    val limitedTime: @NotNull(message = "最大时间未填写") Long? = null
    /**
     * 输入描述
     */
    val inputDescribe: @NotBlank(message = "输入描述未填写") @Size(
        min = 2,
        max = 1000,
        message = "输入描述要求在2~1000字内"
    ) String? = null
    /**
     * 输出描述
     */
    val outputDescribe: @NotBlank(message = "输出描述未填写") @Size(
        min = 2,
        max = 1000,
        message = "输出描述要求在2~1000字内"
    ) String? = null
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
