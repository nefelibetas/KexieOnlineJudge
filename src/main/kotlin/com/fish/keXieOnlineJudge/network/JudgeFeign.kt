package com.fish.keXieOnlineJudge.network

import com.fish.keXieOnlineJudge.entity.dto.judge.RunJudgeDTO
import com.fish.keXieOnlineJudge.entity.dto.judge.RunJudgeResultDTO
import com.fish.keXieOnlineJudge.entity.dto.judge.RunTestDTO
import com.fish.keXieOnlineJudge.entity.dto.judge.RunTestResultDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping

@Service
@FeignClient(name = "JudgeFeign",url = "http://125.217.52.194:8080")
interface JudgeFeign {
    @PostMapping("/v1/code/judge")
    fun runJudge(runJudgeDTO: RunJudgeDTO): RunJudgeResultDTO
    @PostMapping("/v1/code/run")
    fun runTest(runTestDTO: RunTestDTO): RunTestResultDTO
}