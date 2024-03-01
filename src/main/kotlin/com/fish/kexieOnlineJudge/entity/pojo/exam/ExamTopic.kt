package com.fish.kexieOnlineJudge.entity.pojo.exam

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.Table
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_exam_topic")
open class ExamTopic: Serializable {
    @Id
    var examId: Long? = null
    @Id
    var topicId: Long? = null
    var examScore: Long? = null
}
