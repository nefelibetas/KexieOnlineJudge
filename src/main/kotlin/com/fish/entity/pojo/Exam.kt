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
@Table(value = "oj_exam")
data class Exam(
    @Id(keyType = KeyType.Auto)
    var examId: Long?,
    var hostId: String?,

    /**
     * 是否公布成绩
     */
    var opened: Boolean?,

    /**
     * 是否进行排行
     */
    var ranked: Boolean?,
    var describe: String?,

    /**
     * 考试开始时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var startTime: LocalDateTime?,

    /**
     * 结束时间，默认为开始时间后七天
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var endTime: LocalDateTime?,

    /**
     * 该项考试创建时间，和enabled字段搭配实现复用
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var createTime: LocalDateTime?,
    var enabled: Boolean?,
) : Serializable