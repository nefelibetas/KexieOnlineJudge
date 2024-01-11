package com.fish.keXieOpenJudge.controller

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.exam.InsertExamDTO
import com.fish.keXieOpenJudge.entity.dto.exam.UpdateExamDTO
import com.fish.keXieOpenJudge.entity.vo.ExamVO
import com.fish.keXieOpenJudge.service.exam.ExamService
import com.mybatisflex.core.paginate.Page
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ExamController(val examService: ExamService) {
    @GetMapping("/exam/gets")
    fun getExams(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "5") pageSize: Int
    ): Result<Page<ExamVO>> {
        return examService.getExams(pageNo, pageSize)
    }

    @GetMapping("/exam/get/{examId}")
    fun getExam(@PathVariable examId: Long): Result<ExamVO> {
        return examService.getExamById(examId)
    }

    @PostMapping("/admin/exam/add")
    fun addExam(@RequestBody @Valid insertExamDTO: InsertExamDTO): Result<*> {
        return examService.addExam(insertExamDTO)
    }

    @PutMapping("/admin/exam/update")
    fun updateExam(@RequestBody @Valid updateExamDTO: UpdateExamDTO): Result<*> {
        return examService.updateExam(updateExamDTO)
    }

    @PutMapping("/admin/exam/changeStatus/{examId}")
    fun changeStatus(
        @PathVariable examId: Long,
        @RequestParam(defaultValue = "false") action: Boolean
    ): Result<*> {
        return examService.changeStatus(examId, action)
    }
}