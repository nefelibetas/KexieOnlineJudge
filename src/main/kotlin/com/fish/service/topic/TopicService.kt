package com.fish.service.topic

import com.fish.common.Result
import com.fish.entity.pojo.Topic
import com.fish.entity.vo.TopicVO
import com.mybatisflex.core.service.IService

interface TopicService : IService<Topic> {
    /**
     * 新增题目
     * @param topic 题目
     * @return 封装好的响应信息
     */
    fun addTopic(topic: Topic): Result<*>

    /**
     * 批量新增题目
     * @param topics 题目数组
     * @return 封装好的响应信息
     */
    fun addTopicBatch(topics: ArrayList<Topic>): Result<*>

    /**
     * 删除对应题目
     * @param topicId 题目id
     * @return 封装好的响应信息
     */
    fun deleteTopic(topicId: Long): Result<*>

    /**
     * 更新题目信息
     * @param topicId 题目id
     * @param topic 题目传输对象
     * @return 封装好的响应信息
     */
    fun updateTopic(topicId: Long, topic: Topic): Result<*>

    /**
     * 获取所有题目
     * @return 封装好的响应信息
     */
    fun getTopics(): Result<ArrayList<TopicVO>>

    /**
     * 获取id对应的题目
     * @param topicId 题目id
     * @return 封装好的响应信息
     */
    fun getTopic(topicId: Long): Result<TopicVO>
}
