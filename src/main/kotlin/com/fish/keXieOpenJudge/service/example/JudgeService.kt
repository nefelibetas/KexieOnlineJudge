package com.fish.keXieOpenJudge.service.example

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.judge.JudgeDTO
import com.fish.keXieOpenJudge.entity.dto.judge.RunTestDTO
import com.fish.keXieOpenJudge.entity.vo.JudgeResultVO
import com.fish.keXieOpenJudge.entity.vo.TestResultVO

interface JudgeService {
    fun runTest(runTestDTO: RunTestDTO): Result<TestResultVO>
    fun runJudge(judgeDTO: JudgeDTO): Result<JudgeResultVO>
}