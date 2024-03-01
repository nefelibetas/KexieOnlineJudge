package com.fish.keXieOpenJudge.entity.pojo.exam

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
open class Exam: Serializable {
    @Id(keyType = KeyType.Auto) 
    val examId: Long? = null
    val hostId: String? = null
    /**
     * 是否公布成绩
     */
    val opened: Boolean? = null
    /**
     * 是否进行排行
     */
    val ranked: Boolean? = null
    val describe: String? = null
    /**
     * 考试开始时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val startTime: LocalDateTime? = null
    /**
     * 结束时间，默认为开始时间后七天
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val endTime: LocalDateTime? = null
    /**
     * 该项考试创建时间，和enabled字段搭配实现复用
     */
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val createTime: LocalDateTime? = null
    val enabled: Boolean? = null
}
