package com.fish.keXieOpenJudge.service.topic

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.topic.InsertTopicDTO
import com.fish.keXieOpenJudge.entity.dto.topic.UpdateTopicDTO
import com.fish.keXieOpenJudge.entity.pojo.topic.Topic
import com.fish.keXieOpenJudge.entity.vo.TopicVO
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
    fun changeStatus(topicId: Long?, action: Boolean): Result<*>

    /**
     * 更新题目信息
     * @param topicId 题目id
     * @param topic 题目传输对象
     * @return 封装好的响应信息
     */
    fun updateTopic(topicId: Long?, updateTopicDTO: UpdateTopicDTO): Result<*>

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
    fun getTopic(topicId: Long?): Result<TopicVO>

    /**
     * 模糊搜索题目
     * @param keyword 关键词
     */
    fun search(keyword: String, pageNo: Int, pageSize: Int): Result<Page<TopicVO>>
}
