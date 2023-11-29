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
data class Like(
    @Id(keyType = KeyType.Auto)
    var likeId: Long?,
    /**
     * 题解id
     */
    var solutionId: Long?,
    /**
     * 评论id
     */
    var commentId: Long?,
    /**
     * 点赞的用户
     */
    var userId: String?,
) : Serializable {
    override fun toString(): String {
        return "Like(likeId=$likeId, solutionId=$solutionId, commentId=$commentId, userId=$userId)"
    }
}
