package com.fish.entity.pojo

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_like")
class Like : Serializable {
    @Id(keyType = KeyType.Auto)
    var likeId: Long? = null

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
}
