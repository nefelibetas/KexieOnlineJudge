package com.fish.keXieOnlineJudge.entity.vo

import java.io.Serializable

class SecondCommentVO: Serializable {
    val commentId: Long? = null
    val commentContent: String? = null
    val parentId: Long? = null
    var likeNumber: Int? = null
    val accountInfo: AccountVO? = null
}