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
@Table(value = "oj_column_topic")
open class ColumnTopic(columnId: Long, topicId: Long) : Serializable {
    @Id
    var columnId: @NotNull(message = "栏目id不能为空") Long? = null
    @Id
    var topicId: @NotNull(message = "题目id不能为空") Long? = null
}
