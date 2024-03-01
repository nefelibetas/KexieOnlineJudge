package com.fish.kexieOnlineJudge.controller

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.topic.InsertTopicSolutionCommentDTO
import com.fish.kexieOnlineJudge.entity.dto.topic.InsertTopicSolutionDTO
import com.fish.kexieOnlineJudge.entity.vo.PreviewTopicSolution
import com.fish.kexieOnlineJudge.entity.vo.SecondCommentVO
import com.fish.kexieOnlineJudge.entity.vo.TopicSolutionVO
import com.fish.kexieOnlineJudge.service.topic.TopicSolutionCommentService
import com.fish.kexieOnlineJudge.service.topic.TopicSolutionService
import com.mybatisflex.core.paginate.Page
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
/**
 * @author BridgeFish
 * <br/>
 * 题目中题解（包含题解评论）相关的控制器
 */
@RestController
class SolutionController(val topicSolutionService: TopicSolutionService, val topicSolutionCommentService: TopicSolutionCommentService) {
    @PostMapping("/user/topicSolution/add")
    fun addSolution(@RequestBody @Valid insertTopicSolutionDTO: InsertTopicSolutionDTO): Result<*> {
        return topicSolutionService.addSolution(insertTopicSolutionDTO)
    }

    @GetMapping("/topicSolution/get")
    fun getSolution(
        @RequestParam(required = true) solutionId: Long,
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): Result<TopicSolutionVO> {
        return topicSolutionService.getSolution(solutionId, pageNo, pageSize)
    }

    @PostMapping("/user/topicSolution/comment/add")
    fun addComment(@RequestBody @Valid insertTopicSolutionCommentDTO: InsertTopicSolutionCommentDTO): Result<*> {
        return topicSolutionCommentService.addComment(insertTopicSolutionCommentDTO)
    }

    @GetMapping("/topicSolution/gets")
    fun getAllSolutions(
        @RequestParam(required = true) topicId: Long,
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): Result<Page<PreviewTopicSolution>> {
        return topicSolutionService.getAllSolutions(topicId, pageNo, pageSize)
    }

    @GetMapping("/topicSolution/secondComment/get")
    fun getSecondComment(
        @RequestParam(required = true) commentId: Long,
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): Result<Page<SecondCommentVO>> {
        return topicSolutionCommentService.getSecondComment(commentId, pageNo, pageSize)
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

    @PutMapping("/user/topicSolution/comment/delete/{commentId}")
    fun removeComment(@PathVariable commentId: Long): Result<*> {
        return topicSolutionCommentService.deleteComment(commentId)
    }

}