package com.fish.keXieOnlineJudge.entity.vo

import com.fish.keXieOnlineJudge.entity.dto.judge.RunTestResultDTO
import java.io.Serializable

data class JudgeResultVO(
    val status: String,
    val allTime: String,
    val allMemory: String,
    val output: String = "",
    val exampleResult: ArrayList<ExampleVO>
): Serializable

data class ExampleVO(
    val input: String,
    val output: String,
    val cpuTime: String,
    /**
     * 程序使用内存(kb)
     */
    val memory: String,
    /**
     * 程序运行总耗时(ms)
     */
    val realTime: String,
    val assessmentStatus: String
): Serializable

data class TestResultVO(
    val output: String,
    val cpuTime: String,
    val realTime: String,
    val memory: String,
    val status: String
): Serializable {
    constructor(runTestResultDTO: RunTestResultDTO):
            this(runTestResultDTO.outPut, runTestResultDTO.cpuTimeUsage, runTestResultDTO.realTimeUsage, runTestResultDTO.memoryUsage, runTestResultDTO.state)
}