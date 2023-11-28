package com.fish.entity.vo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fish.entity.pojo.Label
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.io.Serializable
import java.time.LocalDateTime

class TopicVO(
    var topicId: Long? = null,
    var uploadUserId: String? = null,
    var title: String? = null,
    var content: String? = null,
    var difficulty: String? = null,
    var limitedMemory: Long? = null,
    var limitedTime: Long? = null,
    var inputDescribe: String? = null,
    var outputDescribe: String? = null,
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var updateTime: LocalDateTime? = null,
    var precautions: String? = null,
    var from: String? = null,
    var labels: ArrayList<Label>? = null,
) : Serializable
