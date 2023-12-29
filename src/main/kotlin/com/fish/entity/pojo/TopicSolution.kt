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
@Table(value = "oj_topic_solutions")
open class TopicSolution(
    solutionId: Long?,
    topicId: Long?,
    title: String?,
    content: String?,
    pined: Boolean?,
    enabled: Boolean?,
    createTime: LocalDateTime?
) : Serializable {
    @Id(keyType = KeyType.Auto)
    val solutionId: Long? = null
    val topicId: Long? = null
    val title: String? = null
    val content: String? = null
    /**
     * 置顶
     */
    val pined: Boolean? = null
    val enabled: Boolean? = null
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val createTime: LocalDateTime? = null
}
