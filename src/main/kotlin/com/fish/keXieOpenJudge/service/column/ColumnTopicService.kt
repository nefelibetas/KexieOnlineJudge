package com.fish.keXieOpenJudge.service.column

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.pojo.column.ColumnTopic
import com.fish.keXieOpenJudge.entity.vo.TopicVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface ColumnTopicService : IService<ColumnTopic> {
    fun getOptionalTopic(columnId: Long, pageNo: Int, pageSize: Int) : Result<Page<TopicVO>>
    fun addTopicToColumn(columnId: Long, topicIds: ArrayList<Long>) : Result<*>
    fun removeTopic(columnId: Long, topicIds: ArrayList<Long>) : Result<*>
}
