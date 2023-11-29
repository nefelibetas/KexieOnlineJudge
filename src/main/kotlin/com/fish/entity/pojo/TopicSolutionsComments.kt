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
class TopicSolutionsComments : Serializable {
    @Id(keyType = KeyType.Auto)
    var commentId: Long? = null
    var userId: String? = null
    var solutionId: Long? = null

    /**
     * 父评论的id，作为鉴别是否为二级评论的标志
     */
    var parentId: Long? = null
    var content: String? = null
    var enabled: Boolean? = null

    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var createTime: LocalDateTime? = null
    override fun toString(): String {
        return "TopicSolutionsComments(commentId=$commentId, userId=$userId, solutionId=$solutionId, parentId=$parentId, content=$content, enabled=$enabled, createTime=$createTime)"
    }

}
