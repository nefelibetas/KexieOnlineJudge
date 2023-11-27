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
@Table(value = "oj_exam_participate")
data class ExamParticipate(
    @Id var examId: Long?,
    @Id var participantId: String?,
) : Serializable