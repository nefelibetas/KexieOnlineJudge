package com.fish.keXieOpenJudge.service.label.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.label.InsertLabelsDTO
import com.fish.keXieOpenJudge.entity.pojo.label.Label
import com.fish.keXieOpenJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.label.LabelMapper
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class LabelServiceImpl : ServiceImpl<LabelMapper, Label>(), com.fish.keXieOpenJudge.service.label.LabelService {
    @Transactional
    override fun addLabel(label: Label): Result<*> {
        val i = mapper!!.insert(label)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun addLabelBatch(labels: InsertLabelsDTO): Result<*> {
        val i = mapper!!.insertBatch(labels.labels)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getLabels(pageNo: Int, pageSize: Int): Result<Page<Label>> {
        val wrapper = QueryWrapper.create().select(LABEL.ALL_COLUMNS).from(LABEL).orderBy(LABEL.LABEL_ID.asc())
        val labelPage = mapper!!.paginate(Page.of(pageNo, pageSize), wrapper)
        return success(labelPage)
    }

    override fun getLabel(labelId: Long): Result<Label> {
        val label = mapper!!.selectOneById(labelId)
        if (!Objects.isNull(label))
            return success(label!!)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    @Transactional
    override fun updateLabel(label: Label): Result<*> {
        if (Objects.isNull(label.labelId))
            throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val wrapper = QueryWrapper.create().select().from(LABEL)
            .where(LABEL.LABEL_ID.eq(label.labelId))
        val i = mapper!!.updateByQuery(label, wrapper)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun deleteLabel(labelId: Long): Result<*> {
        val wrapper1 = QueryWrapper.create()
            .from(TOPIC_LABEL).where(TOPIC_LABEL.LABEL_ID.eq(labelId))
        val i = mapper.deleteByQuery(wrapper1)
        val wrapper2 = QueryWrapper.create()
            .from(LABEL).where(LABEL.LABEL_ID.eq(labelId))
        val j = mapper.deleteByQuery(wrapper2)
        if ((i and j) > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun search(keyword: String, pageNo: Int, pageSize: Int): Result<Page<Label>> {
        val wrapper = QueryWrapper.create().select(LABEL.ALL_COLUMNS).from(LABEL).where(LABEL.LABEL_NAME.like(keyword))
        val labelPage = mapper.paginate(Page.of(pageNo, pageSize), wrapper)
        return success(labelPage)
    }
}
