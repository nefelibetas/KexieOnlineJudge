package com.fish.keXieOnlineJudge.entity.dto.topic

class UpdateTopicDTO {
    val title: String? = null
    /**
     * 题面
     */
    val content: String? = null
    val difficulty: String? = null
    /**
     * 限制内存
     */
    val limitedMemory: Long? = null
    /**
     * 限制时间
     */
    val limitedTime: Long? = null
    /**
     * 输入描述
     */
    val inputDescribe: String? = null
    /**
     * 输出描述
     */
    val outputDescribe:  String? = null
    /**
     * 是否开启题解
     */
    val enabledSolution: Boolean? = null
    /**
     * 注意事项
     */
    val precautions: String? = null
    val from: String? = null
}