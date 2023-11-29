package com.fish.service.column.impl

import com.fish.common.Result
import com.fish.entity.pojo.Column
import com.fish.entity.pojo.table.*
import com.fish.entity.pojo.table.ColumnTableDef.COLUMN
import com.fish.entity.vo.ColumnVO
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import com.fish.mapper.ColumnMapper
import com.fish.service.column.ColumnService
import com.fish.utils.ResultUtil.success
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.spring.service.impl.ServiceImpl
import jakarta.validation.Valid
import org.apache.ibatis.annotations.Update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@Service
class ColumnServiceImpl : ServiceImpl<ColumnMapper, Column>(), ColumnService {
    @Transactional
    override fun addColumn(column: Column): Result<*> {
        val i = mapper!!.insert(column)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun addColumnBatch(columns: @Valid ArrayList<Column>): Result<*> {
        if (columns.isEmpty()) throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val i = mapper!!.insertBatch(columns)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getColumns(): Result<ArrayList<ColumnVO>> {
        val wrapper = QueryWrapper.create()
            .select(ColumnTableDef.COLUMN.ALL_COLUMNS, TopicTableDef.TOPIC.ALL_COLUMNS, LabelTableDef.LABEL.ALL_COLUMNS)
            .from(ColumnTableDef.COLUMN)
            .innerJoin<QueryWrapper>(ColumnTopicTableDef.COLUMN_TOPIC)
            .on(ColumnTopicTableDef.COLUMN_TOPIC.COLUMN_ID.eq(ColumnTableDef.COLUMN.COLUMN_ID))
            .and(ColumnTableDef.COLUMN.ENABLED.eq(true))
            .innerJoin<QueryWrapper>(TopicTableDef.TOPIC)
            .on(TopicTableDef.TOPIC.TOPIC_ID.eq(ColumnTopicTableDef.COLUMN_TOPIC.TOPIC_ID))
            .and(TopicTableDef.TOPIC.ENABLED.eq(true))
            .innerJoin<QueryWrapper>(TopicLabelTableDef.TOPIC_LABEL)
            .on(TopicTableDef.TOPIC.TOPIC_ID.eq(TopicLabelTableDef.TOPIC_LABEL.TOPIC_ID))
            .innerJoin<QueryWrapper>(LabelTableDef.LABEL)
            .on(LabelTableDef.LABEL.LABEL_ID.eq(TopicLabelTableDef.TOPIC_LABEL.LABEL_ID))
        val columnVOS = mapper!!.selectListByQueryAs(wrapper, ColumnVO::class.java) as ArrayList<ColumnVO>
        if (columnVOS.size > 0)
            return success()
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    override fun getColumn(columnId: Long): Result<ColumnVO> {
        val wrapper = QueryWrapper.create()
            .select(ColumnTableDef.COLUMN.ALL_COLUMNS, TopicTableDef.TOPIC.ALL_COLUMNS, LabelTableDef.LABEL.ALL_COLUMNS)
            .from(ColumnTableDef.COLUMN)
            .innerJoin<QueryWrapper>(ColumnTopicTableDef.COLUMN_TOPIC)
            .on(ColumnTopicTableDef.COLUMN_TOPIC.COLUMN_ID.eq(ColumnTableDef.COLUMN.COLUMN_ID))
            .and(ColumnTableDef.COLUMN.ENABLED.eq(true)).and(ColumnTableDef.COLUMN.COLUMN_ID.eq(columnId))
            .innerJoin<QueryWrapper>(TopicTableDef.TOPIC)
            .on(TopicTableDef.TOPIC.TOPIC_ID.eq(ColumnTopicTableDef.COLUMN_TOPIC.TOPIC_ID))
            .and(TopicTableDef.TOPIC.ENABLED.eq(true))
            .innerJoin<QueryWrapper>(TopicLabelTableDef.TOPIC_LABEL)
            .on(TopicTableDef.TOPIC.TOPIC_ID.eq(TopicLabelTableDef.TOPIC_LABEL.TOPIC_ID))
            .innerJoin<QueryWrapper>(LabelTableDef.LABEL)
            .on(LabelTableDef.LABEL.LABEL_ID.eq(TopicLabelTableDef.TOPIC_LABEL.LABEL_ID))
        val columnVO = mapper!!.selectOneByQueryAs(wrapper, ColumnVO::class.java)
        if (!Objects.isNull(columnVO))
            return success(columnVO)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    @Transactional
    override fun updateColumn(column: Column): Result<*> {
        if (Objects.isNull(column.columnId)) throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val i = mapper!!.update(column)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun disableColumn(columnId: Long): Result<*> {
        val i = mapper!!.disableColumn(columnId)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun enableColumn(columnId: Long): Result<*> {
        val update = UpdateChain.of(COLUMN)
            .set(COLUMN.ENABLED, true)
            .where(COLUMN.COLUMN_ID.eq(columnId))
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun deleteColumn(columnId: Long): Result<*> {
        val i = mapper!!.deleteById(columnId)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
}
