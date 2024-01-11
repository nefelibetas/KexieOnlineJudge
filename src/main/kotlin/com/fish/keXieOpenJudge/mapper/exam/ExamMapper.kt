package com.fish.keXieOpenJudge.mapper.exam

import com.fish.keXieOpenJudge.entity.dto.exam.InsertExamDTO
import com.fish.keXieOpenJudge.entity.dto.exam.UpdateExamDTO
import com.fish.keXieOpenJudge.entity.pojo.exam.Exam
import com.mybatisflex.core.BaseMapper

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface ExamMapper : BaseMapper<Exam> {
    fun insertExamDTO(insertExamDTO: InsertExamDTO): Int
    fun updateExamDTO(updateExamDTO: UpdateExamDTO): Int
}
