package com.fish.entity.pojo

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_assessment_result")
data class AssessmentResult(
    @Id(keyType = KeyType.Auto) var resultId: Long?,
    var userId: String?,
    var topicId: Long?,
    var code: String?,
    var hash: String?,
    var examId: Long?,
    var score: Long?,
    var allTime: Long?,
    var status: String?,
) : Serializable {
    override fun toString(): String {
        return "AssessmentResult(resultId=$resultId, userId=$userId, topicId=$topicId, code=$code, hash=$hash, examId=$examId, score=$score, allTime=$allTime, status=$status)"
    }
}
