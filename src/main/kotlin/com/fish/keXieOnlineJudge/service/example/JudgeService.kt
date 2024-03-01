package com.fish.keXieOnlineJudge.service.example

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.dto.judge.JudgeDTO
import com.fish.keXieOnlineJudge.entity.dto.judge.RunTestDTO
import com.fish.keXieOnlineJudge.entity.vo.JudgeResultVO
import com.fish.keXieOnlineJudge.entity.vo.TestResultVO

interface JudgeService {
    fun runTest(runTestDTO: RunTestDTO): Result<TestResultVO>
    fun runJudge(judgeDTO: JudgeDTO): Result<JudgeResultVO>
}