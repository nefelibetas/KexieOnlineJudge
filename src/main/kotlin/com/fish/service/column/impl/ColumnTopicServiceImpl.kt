package com.fish.service.column.impl

import com.fish.entity.pojo.ColumnTopic
import com.fish.entity.pojo.table.ColumnTopicTableDef.COLUMN_TOPIC
import com.fish.entity.pojo.table.LabelTableDef.LABEL
import com.fish.entity.pojo.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.entity.pojo.table.TopicTableDef.TOPIC
import com.fish.entity.vo.TopicVO
import com.fish.mapper.ColumnTopicMapper
import com.fish.service.column.ColumnTopicService
import com.fish.utils.ResultUtil.success
import com.fish.common.Result
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ColumnTopicServiceImpl : ServiceImpl<ColumnTopicMapper, ColumnTopic>(), ColumnTopicService {
    override fun getOptionalTopic(columnId: Long): Result<ArrayList<TopicVO>> {
        val wrapper = QueryWrapper.create()
            .select()
            .from(TOPIC)
            .innerJoin<QueryWrapper>(TOPIC_LABEL).on(TOPIC_LABEL.TOPIC_ID.eq(TOPIC.TOPIC_ID))
            .innerJoin<QueryWrapper>(LABEL).on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
            .where(
                TOPIC.TOPIC_ID.notIn(
                    QueryWrapper.create()
                        .select(TOPIC.TOPIC_ID)
                        .from(TOPIC)
                        .innerJoin<QueryWrapper>(COLUMN_TOPIC)
                        .on(COLUMN_TOPIC.COLUMN_ID.eq(TOPIC.TOPIC_ID))
                        .and(COLUMN_TOPIC.COLUMN_ID.eq(columnId))
                )
            )
        val topicVOS = mapper!!.selectListByQueryAs(wrapper, TopicVO::class.java) as ArrayList<TopicVO>
        return success(topicVOS)
    }

    @Transactional
    override fun addTopicToColumn(columnId: Long, topicIds: ArrayList<Long>): Result<*> {
        val columnTopics = ArrayList<ColumnTopic>()
        topicIds.forEach{ columnTopics.add(ColumnTopic(columnId, it))}
        val i = mapper!!.insertBatch(columnTopics)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun removeTopic(columnId: Long, topicIds: ArrayList<Long>): Result<*> {
        val wrapper = QueryWrapper.create()
            .select().from(COLUMN_TOPIC)
            .where(COLUMN_TOPIC.COLUMN_ID.eq(columnId))
            .and(TOPIC.TOPIC_ID.`in`(topicIds))
        val i = mapper!!.deleteByQuery(wrapper)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

}
