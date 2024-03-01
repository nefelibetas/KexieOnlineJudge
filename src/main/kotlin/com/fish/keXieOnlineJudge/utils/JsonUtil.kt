package com.fish.keXieOnlineJudge.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

object JsonUtil {
    val mapper: ObjectMapper = JsonMapper.builder()
        .addModule(JavaTimeModule())
        .build()
}