package com.fish.keXieOpenJudge.service.topic.impl

import com.fish.keXieOpenJudge.entity.dto.example.InsertExampleDTO
import com.fish.keXieOpenJudge.entity.dto.topic.InsertTopicDTO
import com.fish.keXieOpenJudge.entity.dto.topic.UpdateTopicDTO
import com.fish.keXieOpenJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicTableDef.TOPIC
import com.fish.keXieOpenJudge.entity.pojo.topic.Topic
import com.fish.keXieOpenJudge.entity.vo.TopicVO
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.example.ExampleMapper
import com.fish.keXieOpenJudge.mapper.topic.TopicMapper
import com.fish.keXieOpenJudge.service.topic.TopicLabelService
import com.fish.keXieOpenJudge.service.topic.TopicService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.fish.keXieOpenJudge.common.Result
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TopicServiceImpl(val exampleMapper: ExampleMapper, val topicLabelService: TopicLabelService): ServiceImpl<TopicMapper, Topic>(), TopicService {
    @Transactional
    override fun addTopicWithExample(insertTopicDTO: InsertTopicDTO): Result<*> {
        val topicId = addTopic(insertTopicDTO)
        addExamples(insertTopicDTO.examples!!, topicId)
        topicLabelService.addLabelToTopic(topicId, insertTopicDTO.labelsId!!)
        return success<Any>()
    }
    @Transactional
    fun addTopic(insertTopicDTO: InsertTopicDTO): Long {
        val i = mapper!!.addTopic(insertTopicDTO)
        if (i > 0)
            return insertTopicDTO.topicId!!
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
    @Transactional
    fun addExamples(examples: ArrayList<InsertExampleDTO>, topicId: Long): Boolean {
        examples.forEach {
            it.topicId = topicId
        }
        val i = exampleMapper.addExampleBatch(examples)
        if (i > 0)
            return true
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun changeStatus(topicId: Long, action: Boolean): Result<*> {
        if (Objects.isNull(topicId))
            throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val update = UpdateChain.of(Topic::class.java)
            .set(Topic::enabled.column, action)
            .where(Topic::topicId eq topicId)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun updateTopic(topicId: Long, updateTopicDTO: UpdateTopicDTO): Result<*> {
        if (Objects.isNull(topicId))
            throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val i = mapper!!.updateTopic(topicId, updateTopicDTO)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getTopics(pageNo: Int, pageSize: Int): Result<Page<TopicVO>> {
        val wrapper = QueryWrapper.create()
            .select(TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS).from(TOPIC)
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
        val topicVOS = mapper!!.paginateAs(Page.of(pageNo, pageSize), wrapper, TopicVO::class.java)
        return success(topicVOS)
    }

    override fun getTopic(topicId: Long): Result<TopicVO> {
        if (Objects.isNull(topicId))
            throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val wrapper = QueryWrapper.create()
            .select(TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS).from(TOPIC)
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
            .where(TOPIC.ENABLED.eq(true)).and(TOPIC_LABEL.TOPIC_ID.eq(topicId))
        val topicVO = mapper!!.selectOneByQueryAs(wrapper, TopicVO::class.java)
        if (!Objects.isNull(topicVO))
            return success(topicVO)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }
}