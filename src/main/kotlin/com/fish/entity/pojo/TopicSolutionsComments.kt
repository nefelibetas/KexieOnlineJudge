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
@Table(value = "oj_topic_solutions_comments")
open class TopicSolutionsComments(
    commentId: Long?,
    userId: String?,
    solutionId: Long?,
    parentId: Long?,
    content: String?,
    enabled: Boolean?,
    createTime: LocalDateTime?
) : Serializable {
    @Id(keyType = KeyType.Auto)
    val commentId: Long? = null
    val userId: String? = null
    val solutionId: Long? = null
    /**
     * 父评论的id，作为鉴别是否为二级评论的标志
     */
    val parentId: Long? = null
    val content: String? = null
    val enabled: Boolean? = null
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val createTime: LocalDateTime? = null
}
