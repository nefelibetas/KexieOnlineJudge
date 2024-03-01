package com.fish.kexieOnlineJudge.entity.dto.judge

import com.fish.kexieOnlineJudge.entity.pojo.example.Example
import java.io.Serializable


data class JudgeDTO(
    val topicId: Long,
    val lang: String,
    val code: String,
    val outMsgLimit: Int = 1,
): Serializable

data class RunJudgeDTO(
    /**
     * 程序源代码
     */
    val code: String,
    val lang: String,
    /**
     * 限定程序使用内存(单位: mb [1, 256])
     */
    val memory: Long,
    /**
     * 限定输出内容大小(0 表示使用默认值，单位: kb)
     */
    val outMsgLimit: Int,
    /**
     * 限定运行时间(单位: ms [0, 2000])
     */
    val time: Long,
    /**
     * 样例
     */
    val case: List<Case>
): Serializable

data class Case(
    val caseId: Long,
    val `in`: String,
    val out: String,
): Serializable {
    constructor(example: Example): this(example.exampleId!!, example.input!!, example.output!!)
}

data class RunJudgeResultDTO(
    val state: String,
    val maxTimeUsage: String,
    val maxMemoryUsage: String,
    val outPut: String,
    val codeResults: List<CodeResult>
): Serializable


data class CodeResult(
    val caseId: String,
    /**
     * 程序运行Cpu耗时(ms)
     */
    val cpuTimeUsage: String,
    /**
     * 程序使用内存(kb)
     */
    val memoryUsage: String,
    /**
     * 程序运行总耗时(ms)
     */
    val realTimeUsage: String,
    val state: String
): Serializable