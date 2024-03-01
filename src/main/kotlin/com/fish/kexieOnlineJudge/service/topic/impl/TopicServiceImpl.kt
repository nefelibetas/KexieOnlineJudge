package com.fish.kexieOnlineJudge.service.topic.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.example.InsertExampleDTO
import com.fish.kexieOnlineJudge.entity.dto.topic.InsertTopicDTO
import com.fish.kexieOnlineJudge.entity.dto.topic.UpdateTopicDTO
import com.fish.kexieOnlineJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.kexieOnlineJudge.entity.pojo.topic.Topic
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.TopicTableDef.TOPIC
import com.fish.kexieOnlineJudge.entity.vo.TopicVO
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.example.ExampleMapper
import com.fish.kexieOnlineJudge.mapper.topic.TopicMapper
import com.fish.kexieOnlineJudge.service.topic.TopicLabelService
import com.fish.kexieOnlineJudge.service.topic.TopicService
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicServiceImpl(val exampleMapper: ExampleMapper, val topicLabelService: TopicLabelService): ServiceImpl<TopicMapper, Topic>(), TopicService {
    @Transactional
    override fun addTopicWithExample(insertTopicDTO: InsertTopicDTO): Result<*> {
        insertTopicDTO.examples?.let {
            val topicId = addTopic(insertTopicDTO)
            addExamples(insertTopicDTO.examples, topicId)
            insertTopicDTO.labelsId?.let {
                topicLabelService.addLabelToTopic(topicId, insertTopicDTO.labelsId)
                return success<Any>()
            }
        }
        throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
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
    override fun changeStatus(topicId: Long?, action: Boolean): Result<*> {
        topicId?.let {
            val update = UpdateChain.of(Topic::class.java)
                .set(Topic::enabled.column, action)
                .where(Topic::topicId eq topicId)
                .update()
            if (update)
                return success<Any>()
            throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        }
        throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
    }

    @Transactional
    override fun updateTopic(topicId: Long?, updateTopicDTO: UpdateTopicDTO): Result<*> {
        topicId?.let {
            val i = mapper!!.updateTopic(topicId, updateTopicDTO)
            if (i > 0)
                return success<Any>()
            throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        }
        throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
    }

    override fun getTopics(pageNo: Int, pageSize: Int): Result<Page<TopicVO>> {
        val wrapper = QueryWrapper.create()
            .select(TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS).from(TOPIC)
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
        val topicVOS = mapper!!.paginateWithRelationsAs(Page.of(pageNo, pageSize), wrapper, TopicVO::class.java)
        return success(topicVOS)
    }

    override fun getTopicVO(topicId: Long?): Result<TopicVO> {
        topicId?.let {
            val wrapper = QueryWrapper.create()
                .select(TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS).from(TOPIC)
                .leftJoin<QueryWrapper>(TOPIC_LABEL)
                .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
                .leftJoin<QueryWrapper>(LABEL)
                .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
                .where(TOPIC.ENABLED.eq(true)).and(TOPIC_LABEL.TOPIC_ID.eq(topicId))
            val topicVO = mapper!!.selectOneWithRelationsByQueryAs(wrapper, TopicVO::class.java)
            topicVO?.let {
                return success(topicVO)
            }
            throw ServiceException(ServiceExceptionEnum.NOT_FOUND)

        }
        throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
    }

    override fun search(keyword: String, pageNo: Int, pageSize: Int): Result<Page<TopicVO>> {
        val wrapper = QueryWrapper.create()
            .select(TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS).from(TOPIC)
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
            .where(TOPIC.TITLE.like(keyword))
            .or(TOPIC.CONTENT.like(keyword))
            .or(TOPIC.TOPIC_ID.`in`(
                QueryWrapper.create()
                    .select(TOPIC_LABEL.TOPIC_ID).from(TOPIC_LABEL)
                    .join<QueryWrapper>(LABEL).on(TOPIC_LABEL.LABEL_ID.eq(LABEL.LABEL_ID))
                    .where(LABEL.LABEL_NAME.like(keyword))
            ))
        val topicVOS = mapper!!.paginateAs(Page.of(pageNo, pageSize), wrapper, TopicVO::class.java)
        if (topicVOS.records.size > 0)
            return success(topicVOS)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }
}