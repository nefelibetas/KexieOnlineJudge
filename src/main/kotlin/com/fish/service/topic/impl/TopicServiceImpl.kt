package com.fish.service.topic.impl

import com.fish.common.Result
import com.fish.entity.pojo.Topic
import com.fish.entity.pojo.table.LabelTableDef
import com.fish.entity.pojo.table.TopicLabelTableDef
import com.fish.entity.pojo.table.TopicTableDef
import com.fish.entity.vo.TopicVO
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import com.fish.mapper.TopicMapper
import com.fish.service.topic.TopicService
import com.fish.utils.ResultUtil.success
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicServiceImpl : ServiceImpl<TopicMapper, Topic>(), TopicService {
    @Transactional
    override fun addTopic(topic: Topic): Result<*> {
        val i = mapper!!.insert(topic)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun addTopicBatch(topics: ArrayList<Topic>): Result<*> {
        val i = mapper!!.insertBatch(topics)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun deleteTopic(topicId: Long): Result<*> {
        val i = mapper!!.deleteById(topicId)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun updateTopic(topicId: Long, topic: Topic): Result<*> {
        val wrapper = QueryWrapper.create().where(TopicTableDef.TOPIC.TOPIC_ID.eq(topicId))
        val i = mapper!!.updateByQuery(topic, wrapper)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getTopics(): Result<ArrayList<TopicVO>> {
        val wrapper = QueryWrapper.create()
            .select()
            .from(TopicTableDef.TOPIC)
            .innerJoin<QueryWrapper>(TopicLabelTableDef.TOPIC_LABEL)
            .on(TopicTableDef.TOPIC.TOPIC_ID.eq(TopicLabelTableDef.TOPIC_LABEL.TOPIC_ID))
            .and(TopicTableDef.TOPIC.ENABLED.eq(true))
            .innerJoin<QueryWrapper>(LabelTableDef.LABEL)
            .on(LabelTableDef.LABEL.LABEL_ID.eq(TopicLabelTableDef.TOPIC_LABEL.LABEL_ID))
        val topicVOS = mapper!!.selectListByQueryAs(wrapper, TopicVO::class.java) as ArrayList<TopicVO>
        return success(topicVOS)
    }


    override fun getTopic(topicId: Long): Result<TopicVO> {
        val wrapper = QueryWrapper.create()
            .select()
            .from(TopicTableDef.TOPIC)
            .innerJoin<QueryWrapper>(TopicLabelTableDef.TOPIC_LABEL)
            .on(TopicTableDef.TOPIC.TOPIC_ID.eq(TopicLabelTableDef.TOPIC_LABEL.TOPIC_ID))
            .and(TopicTableDef.TOPIC.ENABLED.eq(true)).and(TopicLabelTableDef.TOPIC_LABEL.TOPIC_ID.eq(topicId))
            .innerJoin<QueryWrapper>(LabelTableDef.LABEL)
            .on(LabelTableDef.LABEL.LABEL_ID.eq(TopicLabelTableDef.TOPIC_LABEL.LABEL_ID))
        val topicVO = mapper!!.selectOneByQueryAs(wrapper, TopicVO::class.java)
        return success(topicVO)
    }
}