package com.fish.keXieOpenJudge.controller

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.solution.InsertSolutionDTO
import com.fish.keXieOpenJudge.entity.vo.PreviewTopicSolution
import com.fish.keXieOpenJudge.entity.vo.TopicSolutionVO
import com.fish.keXieOpenJudge.service.topic.TopicSolutionService
import com.mybatisflex.core.paginate.Page
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
class SolutionController(val topicSolutionService: TopicSolutionService) {
    @PostMapping("/user/topicSolution/add")
    fun addSolution(@RequestBody @Valid insertSolutionDTO: InsertSolutionDTO): Result<*> {
        return topicSolutionService.addSolution(insertSolutionDTO)
    }
    @GetMapping("/topicSolution/get/{solutionId}")
    fun getSolution(@PathVariable solutionId: Long): Result<TopicSolutionVO> {
        return topicSolutionService.getSolution(solutionId)
    }
    @GetMapping("/topicSolution/gets")
    fun getAllSolutions(
        @RequestParam(required = true) topicId: Long,
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): Result<Page<PreviewTopicSolution>> {
        return topicSolutionService.getAllSolutions(topicId, pageNo, pageSize)
    }
    @PutMapping("/admin/topicSolution/changePined/{topicId}/{solutionId}")
    fun changePined(
        @PathVariable topicId: Long,
        @PathVariable solutionId: Long,
        @RequestParam(defaultValue = "true") action: Boolean
    ): Result<*> {
        return topicSolutionService.changePined(topicId, solutionId, action)
    }
    @PutMapping("/admin/topicSolution/changeStatus/{topicId}/{solutionId}")
    fun changeStatus(
        @PathVariable topicId: Long,
        @PathVariable solutionId: Long,
        @RequestParam(defaultValue = "false") action: Boolean
    ): Result<*> {
        return topicSolutionService.changeStatus(topicId, solutionId, action)
    }
}