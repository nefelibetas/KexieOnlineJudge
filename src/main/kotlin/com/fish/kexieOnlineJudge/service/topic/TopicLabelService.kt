package com.fish.kexieOnlineJudge.service.topic

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.pojo.label.Label
import com.fish.kexieOnlineJudge.entity.pojo.topic.TopicLabel
import com.mybatisflex.core.paginate.Page
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
    fun getOptionalLabels(topicId: Long, pageNo: Int, pageSize: Int): Result<Page<Label>>

    /**
     * 给题目批量移除标签
     * @param topicId 题目id
     * @param labelsIds 一些标签id
     * @return 封装好的响应信息
     */
    fun removeLabels(topicId: Long, labelsIds: ArrayList<Long>): Result<*>
}
