package com.fish.kexieOnlineJudge.entity.pojo.topic

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
open class TopicLabel: Serializable {
    @Id
    var labelId: Long? = null
    @Id
    var topicId: Long? = null
}
