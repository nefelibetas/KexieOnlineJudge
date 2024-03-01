package com.fish.keXieOpenJudge.controller

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.judge.JudgeDTO
import com.fish.keXieOpenJudge.entity.dto.judge.RunTestDTO
import com.fish.keXieOpenJudge.entity.vo.TestResultVO
import com.fish.keXieOpenJudge.service.example.JudgeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JudgeController(val judgeService: JudgeService) {
    /**
     * 判断代码是否通过该题
     *
     * 会判断所有的样例
     *
     * 全部通过 -> 通过
     */
    @PostMapping("/user/code/judge")
    fun doJudge(@RequestBody judgeDTO: JudgeDTO): Result<*> = judgeService.runJudge(judgeDTO)
    /**
     * 运行测试
     *
     * 返回允许结果
     */
    @PostMapping("/user/code/run")
    fun doRun(@RequestBody runTestDTO: RunTestDTO): Result<TestResultVO> = judgeService.runTest(runTestDTO)
}