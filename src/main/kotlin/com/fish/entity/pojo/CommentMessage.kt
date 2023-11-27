package com.fish.entity.pojo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_comment_message")
class CommentMessage : Serializable {
    @Id(keyType = KeyType.Auto)
    var commentId: Long? = null
    var content: String? = null

    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var sendTime: LocalDateTime? = null
}