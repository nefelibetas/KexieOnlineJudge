package com.fish.kexieOnlineJudge.service.example

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.judge.JudgeDTO
import com.fish.kexieOnlineJudge.entity.dto.judge.RunTestDTO
import com.fish.kexieOnlineJudge.entity.vo.JudgeResultVO
import com.fish.kexieOnlineJudge.entity.vo.TestResultVO

interface JudgeService {
    fun runTest(runTestDTO: RunTestDTO): Result<TestResultVO>
    fun runJudge(judgeDTO: JudgeDTO): Result<JudgeResultVO>
}