package com.fish.kexieOnlineJudge.entity.pojo.topic

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.Table
import jakarta.validation.constraints.NotNull
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_accepted")
open class Accepted: Serializable {
    @Id
    @NotNull(message = "用户Id未填写")
    val userId: String? = null
    @Id
    @NotNull(message = "题目Id未填写")
    val topicId: Long? = null
}
