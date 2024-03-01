package com.fish.keXieOnlineJudge.entity.pojo.message

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
open class Message: Serializable {
    @Id(keyType = KeyType.Auto)
    val messageId: Long? = null
    val typeId: Long? = null
    val title: String? = null
    val content: String? = null
    val sendToUserId: String? = null
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val sendTime: LocalDateTime? = null
    var notified: Boolean = true
}
