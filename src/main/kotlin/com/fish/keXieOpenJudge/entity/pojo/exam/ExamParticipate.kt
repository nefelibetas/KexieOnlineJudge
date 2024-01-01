package com.fish.keXieOpenJudge.entity.pojo.exam

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.Table
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_exam_participate")
open class ExamParticipate: Serializable {
    @Id
    val examId: Long? = null
    @Id
    val participantId: String? = null
}