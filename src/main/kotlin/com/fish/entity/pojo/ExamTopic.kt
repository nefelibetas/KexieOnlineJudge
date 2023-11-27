package com.fish.entity.pojo

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.Table
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_exam_topic")
class ExamTopic : Serializable {
    @Id
    var examId: Long? = null

    @Id
    var topicId: Long? = null
    var examScore: Long? = null
}
