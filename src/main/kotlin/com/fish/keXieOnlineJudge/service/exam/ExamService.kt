package com.fish.keXieOnlineJudge.service.exam

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.dto.exam.InsertExamDTO
import com.fish.keXieOnlineJudge.entity.dto.exam.UpdateExamDTO
import com.fish.keXieOnlineJudge.entity.pojo.exam.Exam
import com.fish.keXieOnlineJudge.entity.vo.ExamVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface ExamService: IService<Exam> {
    fun getExams(pageNo: Int, pageSize: Int): Result<Page<ExamVO>>
    fun getExamById(examId: Long): Result<ExamVO>
    fun addExam(insertExamDTO: InsertExamDTO): Result<*>
    fun updateExam(updateExamDTO: UpdateExamDTO): Result<*>
    fun changeStatus(examId: Long, action: Boolean): Result<*>
}