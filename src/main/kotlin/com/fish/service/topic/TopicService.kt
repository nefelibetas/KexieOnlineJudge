package com.fish.service.topic

import com.fish.common.Result
import com.fish.entity.dto.InsertTopicDTO
import com.fish.entity.dto.UpdateTopicDTO
import com.fish.entity.pojo.Topic
import com.fish.entity.vo.TopicVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface TopicService : IService<Topic> {
    /**
     * 新增题目
     * @param topic 题目
     * @return 封装好的响应信息
     */
    fun addTopicWithExample(insertTopicDTO: InsertTopicDTO): Result<*>

    /**
     * 禁用/启用 题目
     * @param topicId 题目id
     * @return 封装好的响应信息
     */
    fun changeStatus(topicId: Long, action: Boolean): Result<*>

    /**
     * 更新题目信息
     * @param topicId 题目id
     * @param topic 题目传输对象
     * @return 封装好的响应信息
     */
    fun updateTopic(topicId: Long, updateTopicDTO: UpdateTopicDTO): Result<*>

    /**
     * 获取所有题目
     * @return 封装好的响应信息
     */
    fun getTopics(pageNo: Int, pageSize: Int): Result<Page<TopicVO>>

    /**
     * 获取id对应的题目
     * @param topicId 题目id
     * @return 封装好的响应信息
     */
    fun getTopic(topicId: Long): Result<TopicVO>
}
