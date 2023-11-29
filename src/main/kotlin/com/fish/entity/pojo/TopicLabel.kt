package com.fish.entity.pojo

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.Table
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_topic_label")
data class TopicLabel(
    @Id var topicId: Long?,
    @Id var labelId: Long?
) : Serializable {
    override fun toString(): String {
        return "TopicLabel(topicId=$topicId, labelId=$labelId)"
    }
}