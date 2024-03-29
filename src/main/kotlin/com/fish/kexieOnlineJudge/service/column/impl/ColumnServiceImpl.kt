package com.fish.kexieOnlineJudge.service.column.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.column.InsertColumnDTO
import com.fish.kexieOnlineJudge.entity.dto.column.UpdateColumnDTO
import com.fish.kexieOnlineJudge.entity.pojo.column.Column
import com.fish.kexieOnlineJudge.entity.pojo.column.table.ColumnTableDef.COLUMN
import com.fish.kexieOnlineJudge.entity.pojo.column.table.ColumnTopicTableDef.COLUMN_TOPIC
import com.fish.kexieOnlineJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.TopicTableDef.TOPIC
import com.fish.kexieOnlineJudge.entity.vo.ColumnVO
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.column.ColumnMapper
import com.fish.kexieOnlineJudge.service.column.ColumnService
import com.fish.kexieOnlineJudge.service.column.ColumnTopicService
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
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
        insertColumnDTO.topicIds?.let {
            val queryWrapper = QueryWrapper.create().select().from(TOPIC).where(TOPIC.TOPIC_ID.`in`(insertColumnDTO.topicIds))
            val size = mapper.selectCountByQuery(queryWrapper)
            if (size != insertColumnDTO.topicIds.size.toLong())
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
            .leftJoin<QueryWrapper>(COLUMN_TOPIC)
            .on(COLUMN_TOPIC.COLUMN_ID.eq(COLUMN.COLUMN_ID))
            .and(COLUMN.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC)
            .on(TOPIC.TOPIC_ID.eq(COLUMN_TOPIC.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
        val columnVOPage = mapper!!.paginateAs(Page.of(pageNo, pageSize), wrapper, ColumnVO::class.java)
        return success(columnVOPage)
    }

    override fun getColumn(columnId: Long): Result<ColumnVO> {
        val wrapper = QueryWrapper.create()
            .select(COLUMN.ALL_COLUMNS, TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS)
            .from(COLUMN)
            .leftJoin<QueryWrapper>(COLUMN_TOPIC)
            .on(COLUMN_TOPIC.COLUMN_ID.eq(COLUMN.COLUMN_ID))
            .and(COLUMN.ENABLED.eq(true)).and(COLUMN.COLUMN_ID.eq(columnId))
            .leftJoin<QueryWrapper>(TOPIC)
            .on(TOPIC.TOPIC_ID.eq(COLUMN_TOPIC.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
        val columnVO = mapper!!.selectOneByQueryAs(wrapper, ColumnVO::class.java)
        columnVO?.let {
            return success(columnVO)
        }
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
        val wrapper1 = QueryWrapper.create()
            .from(COLUMN_TOPIC).where(COLUMN_TOPIC.COLUMN_ID.eq(columnId))
        val i = mapper!!.deleteByQuery(wrapper1)
        val wrapper2 = QueryWrapper.create()
            .from(COLUMN).where(COLUMN.COLUMN_ID.eq(columnId))
        val j = mapper.deleteByQuery(wrapper2)
        if ((i and j) > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun search(keyword: String, pageNo: Int, pageSize: Int): Result<Page<ColumnVO>> {
        val wrapper = QueryWrapper.create()
            .select(COLUMN.ALL_COLUMNS, TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS)
            .from(COLUMN)
            .leftJoin<QueryWrapper>(COLUMN_TOPIC)
            .on(COLUMN_TOPIC.COLUMN_ID.eq(COLUMN.COLUMN_ID))
            .and(COLUMN.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC)
            .on(TOPIC.TOPIC_ID.eq(COLUMN_TOPIC.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
            .where(COLUMN.COLUMN_NAME.like(keyword))
            .or(COLUMN.COLUMN_DESCRIBE.like(keyword))
        val paginateAs = mapper!!.paginateAs(Page.of(pageNo, pageSize), wrapper, ColumnVO::class.java)
        return success(paginateAs)
    }
}
