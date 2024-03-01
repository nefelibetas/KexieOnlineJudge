package com.fish.keXieOnlineJudge.entity.dto.judge

import java.io.Serializable

data class RunTestDTO(
    /**
     * 程序源代码
     */
    val code: String,
    /**
     * 程序输入内容
     */
    val input: String,

    val lang: String,
    /**
     * 限定程序使用内存(单位: mb [1, 256])
     */
    val memory: Long,
    /**
     * 限定输出内容大小(0 表示使用默认值，单位: kb)
     */
    val outMsgLimit: Long,
    /**
     * 限定运行时间(单位: ms [0, 2000])
     */
    val time: Long
): Serializable


data class RunTestResultDTO(
    val outPut: String,
    val cpuTimeUsage: String,
    val realTimeUsage: String,
    val memoryUsage: String,
    val state: String
): Serializable


