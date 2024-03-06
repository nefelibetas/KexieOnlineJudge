package com.fish.kexieOnlineJudge.controller

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.judge.JudgeDTO
import com.fish.kexieOnlineJudge.entity.dto.judge.RunTestDTO
import com.fish.kexieOnlineJudge.entity.vo.JudgeResultVO
import com.fish.kexieOnlineJudge.entity.vo.TestResultVO
import com.fish.kexieOnlineJudge.service.example.JudgeService
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
    fun doJudge(@RequestBody judgeDTO: JudgeDTO): Result<JudgeResultVO> = judgeService.runJudge(judgeDTO)
    /**
     * 运行测试
     *
     * 返回运行结果
     */
    @PostMapping("/user/code/run")
    fun doRun(@RequestBody runTestDTO: RunTestDTO): Result<TestResultVO> = judgeService.runTest(runTestDTO)

}