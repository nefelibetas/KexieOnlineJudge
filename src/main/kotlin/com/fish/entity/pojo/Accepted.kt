package com.fish.entity.pojo

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
data class Accepted(
    @Id var userId: @NotNull(message = "用户Id未填写") String?,
    @Id var topicId: @NotNull(message = "题目Id未填写") Long?
) : Serializable
