package com.fish.keXieOpenJudge.entity.vo

import java.io.Serializable

data class ColumnVO(
    var columnId: Long? = null,
    var columnName: String? = null,
    var columnDescribe: String? = null,
    var enabled: Boolean? = null,
    var topicVOS: ArrayList<TopicVO>? = null
) : Serializable
