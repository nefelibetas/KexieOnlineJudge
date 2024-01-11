package com.fish.keXieOpenJudge.entity.vo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.RelationOneToOne
import java.io.Serializable
import java.time.LocalDateTime

class ExamVO: Serializable {
    val examId: Long? = null
    @JsonIgnore
    var hostId: String? = null
    val describe: String? = null
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val startTime: LocalDateTime? = null
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val endTime: LocalDateTime? = null
    @RelationOneToOne(selfField = "hostId", targetField = "userId", targetTable = "oj_account")
    var host: AccountVO? = null
    var topicVOS: ArrayList<TopicVO>? = null
    var participantNumber: Long? = null
}