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
@Table(value = "oj_message")
class Message : Serializable {
    @Id(keyType = KeyType.Auto) var messageId: Long? = null
    var commentId: Long? = null
    var typeId: Long? = null
    var content: String? = null
    var sendTo: String? = null

    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var sendTime: LocalDateTime? = null
    override fun toString(): String {
        return "Message(messageId=$messageId, commentId=$commentId, typeId=$typeId, content=$content, sendTo=$sendTo, sendTime=$sendTime)"
    }

}
