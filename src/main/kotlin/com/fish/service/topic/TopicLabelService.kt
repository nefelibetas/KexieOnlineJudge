package com.fish.service.topic

import com.fish.common.Result
import com.fish.entity.pojo.Label
import com.fish.entity.pojo.TopicLabel
import com.mybatisflex.core.service.IService

interface TopicLabelService : IService<TopicLabel> {
    /**
     * 给题目添加标签
     * @param topicId 题目id
     * @param labelIds 一些标签id
     * @return 封装好的响应信息
     */
    fun addLabelToTopic(topicId: Long, labelIds: ArrayList<Long>): Result<*>

    /**
     * 获取可选的id
     * @param topicId 题目id
     * @return 获取该题目id可选的标签
     */
    fun getOptionalLabels(topicId: Long): Result<ArrayList<Label>>

    /**
     * 给题目批量移除标签
     * @param topicId 题目id
     * @param labelsIds 一些标签id
     * @return 封装好的响应信息
     */
    fun removeLabels(topicId: Long, labelsIds: ArrayList<Long>): Result<*>
}
