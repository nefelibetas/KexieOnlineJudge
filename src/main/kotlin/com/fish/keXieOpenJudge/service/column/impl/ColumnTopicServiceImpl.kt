package com.fish.keXieOpenJudge.service.column.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.pojo.column.ColumnTopic
import com.fish.keXieOpenJudge.entity.pojo.column.table.ColumnTopicTableDef.COLUMN_TOPIC
import com.fish.keXieOpenJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicTableDef.TOPIC
import com.fish.keXieOpenJudge.entity.vo.TopicVO
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.column.ColumnTopicMapper
import com.fish.keXieOpenJudge.service.column.ColumnTopicService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ColumnTopicServiceImpl: ServiceImpl<ColumnTopicMapper, ColumnTopic>(), ColumnTopicService {
    override fun getOptionalTopic(columnId: Long, pageNo: Int, pageSize: Int): Result<Page<TopicVO>> {
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
        val paginateAs = mapper!!.paginateAs(Page.of(pageNo, pageSize), wrapper, TopicVO::class.java)
        return success(paginateAs)
    }

    @Transactional
    override fun addTopicToColumn(columnId: Long, topicIds: ArrayList<Long>): Result<*> {

        val columnTopics = ArrayList<ColumnTopic>()
        for(i in 0 until topicIds.size) {
            val columnTopic = ColumnTopic()
            columnTopic.columnId = columnId
            columnTopic.topicId = topicIds[i]
            columnTopics.add(columnTopic)
        }
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
