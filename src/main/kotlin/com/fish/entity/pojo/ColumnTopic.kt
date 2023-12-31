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
open class ColumnTopic: Serializable {
    @Id
    @NotNull(message = "栏目id不能为空")
    var columnId: Long? = null
    @Id
    @NotNull(message = "题目id不能为空")
    var topicId: Long? = null
}
