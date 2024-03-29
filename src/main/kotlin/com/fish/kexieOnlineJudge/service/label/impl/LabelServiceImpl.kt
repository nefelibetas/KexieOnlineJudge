package com.fish.kexieOnlineJudge.service.label.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.label.InsertLabelsDTO
import com.fish.kexieOnlineJudge.entity.pojo.label.Label
import com.fish.kexieOnlineJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.label.LabelMapper
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LabelServiceImpl : ServiceImpl<LabelMapper, Label>(), com.fish.kexieOnlineJudge.service.label.LabelService {
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
        label?.let {
            return success(label)
        }
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    @Transactional
    override fun updateLabel(label: Label): Result<*> {
        label.labelId?.let {
            val wrapper = QueryWrapper.create().select().from(LABEL)
                .where(LABEL.LABEL_ID.eq(label.labelId))
            val i = mapper!!.updateByQuery(label, wrapper)
            if (i > 0)
                return success<Any>()
            throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        }
        throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
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
