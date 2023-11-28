package com.fish.service.label.impl

import com.fish.common.Result
import com.fish.entity.pojo.Label
import com.fish.entity.pojo.table.LabelTableDef
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import com.fish.mapper.LabelMapper
import com.fish.service.label.LabelService
import com.fish.utils.ResultUtil.success
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@Service
class LabelServiceImpl : ServiceImpl<LabelMapper, Label>(), LabelService {
    @Transactional
    override fun addLabel(label: Label): Result<*> {
        val i = mapper!!.insert(label)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun addLabelBatch(labels: ArrayList<Label>): Result<*> {
        if (labels.isEmpty()) throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val i = mapper!!.insertBatch(labels)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getLabels(): Result<ArrayList<Label>> {
        val labels = mapper!!.selectAll() as ArrayList<Label>
        return success(labels)
    }

    override fun getLabel(labelId: Long): Result<Label> {
        val label = mapper!!.selectOneById(labelId)
        if (!Objects.isNull(label))
            return success(label!!)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    @Transactional
    override fun updateLabel(label: Label): Result<*> {
        if (Objects.isNull(label.labelId)) throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val wrapper = QueryWrapper.create().select().from(LabelTableDef.LABEL)
            .where(LabelTableDef.LABEL.LABEL_ID.eq(label.labelId))
        val i = mapper!!.updateByQuery(label, wrapper)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun deleteLabel(labelId: Long): Result<*> {
        val i = mapper!!.deleteById(labelId)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
}
