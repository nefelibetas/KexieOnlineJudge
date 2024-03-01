package com.fish.kexieOnlineJudge.entity.dto.exam

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import jakarta.validation.constraints.NotNull
import java.io.Serializable
import java.time.LocalDateTime

class UpdateExamDTO: Serializable {
    @NotNull(message = "试卷id不能为空")
    val examId: Long? = null
    val opened: Boolean? = null
    val ranked: Boolean? = null
    val describe: String? = null
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val startTime: LocalDateTime? = null
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val endTime: LocalDateTime? = null
}