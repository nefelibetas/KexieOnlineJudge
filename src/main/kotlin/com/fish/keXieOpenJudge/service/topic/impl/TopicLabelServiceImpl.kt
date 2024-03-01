package com.fish.keXieOpenJudge.service.topic.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.pojo.label.Label
import com.fish.keXieOpenJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.keXieOpenJudge.entity.pojo.topic.TopicLabel
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.label.LabelMapper
import com.fish.keXieOpenJudge.mapper.topic.TopicLabelMapper
import com.fish.keXieOpenJudge.service.topic.TopicLabelService
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicLabelServiceImpl : ServiceImpl<TopicLabelMapper, TopicLabel>(), TopicLabelService {
    @Resource
    var labelMapper: LabelMapper? = null
    @Transactional
    override fun addLabelToTopic(topicId: Long, labelIds: ArrayList<Long>): Result<*> {
        val topicLabels = ArrayList<TopicLabel>()
        for (i in 0 until labelIds.size) {
            val topicLabel = TopicLabel()
            topicLabel.labelId = labelIds[i]
            topicLabel.topicId = topicId
            topicLabels.add(topicLabel)
        }
        val i = mapper!!.insertBatch(topicLabels)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getOptionalLabels(topicId: Long, pageNo: Int, pageSize: Int): Result<Page<Label>> {
        val wrapper = QueryWrapper.create()
            .select(LABEL.ALL_COLUMNS).from(LABEL)
            .where(LABEL.LABEL_ID.notIn(
                    QueryWrapper.create()
                        .select(LABEL.LABEL_ID)
                        .from(LABEL)
                        .innerJoin<QueryWrapper>(TOPIC_LABEL)
                        .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
                        .and(TOPIC_LABEL.TOPIC_ID.eq(topicId))
                )
            ).orderBy(LABEL.LABEL_ID.asc())
        val labels = labelMapper!!.paginate(Page.of(pageNo, pageSize), wrapper)
        return success(labels)
    }

    @Transactional
    override fun removeLabels(topicId: Long, labelsIds: ArrayList<Long>): Result<*> {
        val wrapper = QueryWrapper.create()
            .select().from(TOPIC_LABEL)
            .where(TOPIC_LABEL.TOPIC_ID.eq(topicId))
            .and(TOPIC_LABEL.LABEL_ID.`in`(labelsIds))
        val i = mapper!!.deleteByQuery(wrapper)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
}
