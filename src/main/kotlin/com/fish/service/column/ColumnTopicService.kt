package com.fish.service.column

import com.fish.common.Result
import com.fish.entity.pojo.ColumnTopic
import com.fish.entity.vo.TopicVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface ColumnTopicService : IService<ColumnTopic> {
    fun getOptionalTopic(columnId: Long, pageNo: Int, pageSize: Int) : Result<Page<TopicVO>>
    fun addTopicToColumn(columnId: Long, topicIds: ArrayList<Long>) : Result<*>
    fun removeTopic(columnId: Long, topicIds: ArrayList<Long>) : Result<*>
}
