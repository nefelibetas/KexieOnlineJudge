package com.fish.entity.pojo

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
open class Like(
    likeId: Long?,
    solutionId: Long?,
    commentId: Long?,
    userId: String?
) : Serializable {
    @Id(keyType = KeyType.Auto)
    val likeId: Long? = null
    /**
     * 题解id
     */
    val solutionId: Long? = null
    /**
     * 评论id
     */
    val commentId: Long? = null
    /**
     * 点赞的用户
     */
    val userId: String? = null
}
