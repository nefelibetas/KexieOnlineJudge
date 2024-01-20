package com.fish.keXieOpenJudge.entity.pojo.topic

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_like")
open class Like(): Serializable {
    @Id(keyType = KeyType.Auto)
    val likeId: Long? = null
    /**
     * 题解id
     */
    var solutionId: Long? = null
    /**
     * 评论id
     */
    var commentId: Long? = null
    /**
     * 点赞的用户
     */
    var userId: String? = null
    constructor(solutionId: Long?, commentId: Long?, userId: String): this() {
        this.solutionId = solutionId
        this.commentId = commentId
        this.userId = userId
    }
}
