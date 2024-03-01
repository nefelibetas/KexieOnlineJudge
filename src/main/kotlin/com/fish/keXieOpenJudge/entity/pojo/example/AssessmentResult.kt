package com.fish.keXieOpenJudge.entity.pojo.example

import com.fish.keXieOpenJudge.entity.dto.judge.JudgeDTO
import com.fish.keXieOpenJudge.entity.dto.judge.RunJudgeResultDTO
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
open class AssessmentResult(): Serializable {
    @Id(keyType = KeyType.Auto)
    val resultId: Long? = null
    var userId: String? = null
    var topicId: Long? = null
    var code: String? = null
    var hash: String? = null
    val examId: Long? = null
    val score: String? = null
    var allTime: String? = null
    var allMemory: String? = null
    var status: String? = null
    constructor(id: String, hash: String, judgeDTO: JudgeDTO, judgeResultDTO: RunJudgeResultDTO): this() {
        this.userId = id
        this.hash = hash
        this.code = judgeDTO.code
        this.topicId = judgeDTO.topicId
        this.allTime = judgeResultDTO.maxTimeUsage
        this.allMemory = judgeResultDTO.maxMemoryUsage
        this.status = judgeResultDTO.state
    }
}
