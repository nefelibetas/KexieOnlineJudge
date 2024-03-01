package com.fish.keXieOpenJudge.entity.vo

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

data class UploadAccount(
    @JsonIgnore
    var userId: String,
    val nickname: String,
    val avatar: String
): Serializable