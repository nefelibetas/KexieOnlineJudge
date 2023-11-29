package com.fish.service.column

import com.fish.common.Result
import com.fish.entity.pojo.ColumnTopic
import com.fish.entity.vo.TopicVO
import com.mybatisflex.core.service.IService

interface ColumnTopicService : IService<ColumnTopic> {
    fun getOptionalTopic(columnId: Long) : Result<ArrayList<TopicVO>>
    fun addTopicToColumn(columnId: Long, topicIds: ArrayList<Long>) : Result<*>
    fun removeTopic(columnId: Long, topicIds: ArrayList<Long>) : Result<*>
}