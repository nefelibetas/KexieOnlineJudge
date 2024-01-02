package com.fish.keXieOpenJudge.entity.pojo.example

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
open class AssessmentResult: Serializable {
    @Id(keyType = KeyType.Auto)
    val resultId: Long? = null
    val userId: String? = null
    val topicId: Long? = null
    val code: String? = null
    val hash: String? = null
    val examId: Long? = null
    val score: Long? = null
    val allTime: Long? = null
    val status: String? = null
}
