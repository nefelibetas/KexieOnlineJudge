package com.fish.entity.pojo

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
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
@Table(value = "oj_assessment_result")
class AssessmentResult : Serializable {
    @Id(keyType = KeyType.Auto)
    var resultId: Long? = null
    var userId: String? = null
    var topicId: Long? = null
    var code: String? = null
    var hash: String? = null
    var examId: Long? = null
    var score: Long? = null
    var allTime: Long? = null
    var status: String? = null
}
