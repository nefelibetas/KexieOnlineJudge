package com.fish.keXieOpenJudge.service.column.impl

import com.fish.keXieOpenJudge.entity.dto.column.InsertColumnDTO
import com.fish.keXieOpenJudge.entity.dto.column.UpdateColumnDTO
import com.fish.keXieOpenJudge.entity.pojo.column.Column
import com.fish.keXieOpenJudge.entity.pojo.column.table.ColumnTableDef.COLUMN
import com.fish.keXieOpenJudge.entity.pojo.column.table.ColumnTopicTableDef.COLUMN_TOPIC
import com.fish.keXieOpenJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.keXieOpenJudge.entity.pojo.topic.table.TopicTableDef.TOPIC
import com.fish.keXieOpenJudge.entity.vo.ColumnVO
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.column.ColumnMapper
import com.fish.keXieOpenJudge.service.column.ColumnService
import com.fish.keXieOpenJudge.service.column.ColumnTopicService
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
class ColumnServiceImpl(val columnTopicService: ColumnTopicService) : ServiceImpl<ColumnMapper, Column>(), ColumnService {
    @Transactional
    override fun addWithTopic(insertColumnDTO: InsertColumnDTO): Result<*> {
        val columnId = addColumn(insertColumnDTO)
        if (!Objects.isNull(insertColumnDTO.topicIds)) {
            val queryWrapper = QueryWrapper.create().select().from(TOPIC).where(TOPIC.TOPIC_ID.`in`(insertColumnDTO.topicIds))
            val size = mapper.selectCountByQuery(queryWrapper)
            if (size != insertColumnDTO.topicIds!!.size.toLong())
                throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
            return columnTopicService.addTopicToColumn(columnId, insertColumnDTO.topicIds)
        }
        return success<Any>()
    }

    @Transactional
    fun addColumn(insertColumnDTO: InsertColumnDTO): Long {
        val i = mapper.insertColumn(insertColumnDTO)
        if (i > 0)
            return insertColumnDTO.columnId!!
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getColumns(pageNo: Int, pageSize: Int): Result<Page<ColumnVO>> {
        val wrapper = QueryWrapper.create()
            .select(COLUMN.ALL_COLUMNS, TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS)
            .from(COLUMN)
            .innerJoin<QueryWrapper>(COLUMN_TOPIC)
            .on(COLUMN_TOPIC.COLUMN_ID.eq(COLUMN.COLUMN_ID))
            .and(COLUMN.ENABLED.eq(true))
            .innerJoin<QueryWrapper>(TOPIC)
            .on(TOPIC.TOPIC_ID.eq(COLUMN_TOPIC.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .innerJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .innerJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
        val columnVOPage = mapper!!.paginateAs(Page.of(pageNo, pageSize), wrapper, ColumnVO::class.java)
        return success(columnVOPage)
    }

    override fun getColumn(columnId: Long): Result<ColumnVO> {
        val wrapper = QueryWrapper.create()
            .select(COLUMN.ALL_COLUMNS, TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS)
            .from(COLUMN)
            .innerJoin<QueryWrapper>(COLUMN_TOPIC)
            .on(COLUMN_TOPIC.COLUMN_ID.eq(COLUMN.COLUMN_ID))
            .and(COLUMN.ENABLED.eq(true)).and(COLUMN.COLUMN_ID.eq(columnId))
            .innerJoin<QueryWrapper>(TOPIC)
            .on(TOPIC.TOPIC_ID.eq(COLUMN_TOPIC.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .innerJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .innerJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
        val columnVO = mapper!!.selectOneByQueryAs(wrapper, ColumnVO::class.java)
        if (!Objects.isNull(columnVO))
            return success(columnVO)
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    @Transactional
    override fun updateColumn(column: UpdateColumnDTO): Result<*> {
        if (Objects.isNull(column.columnId))
            throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
        val i = mapper!!.updateInfo(column)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun changeStatus(columnId: Long, action: Boolean): Result<*> {
        val update = UpdateChain.of(Column::class.java)
            .set(Column::enabled.column, action)
            .where(Column::columnId eq columnId)
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
