package com.fish.keXieOpenJudge.service.column

import com.fish.keXieOpenJudge.entity.dto.column.InsertColumnDTO
import com.fish.keXieOpenJudge.entity.dto.column.UpdateColumnDTO
import com.fish.keXieOpenJudge.entity.pojo.column.Column
import com.fish.keXieOpenJudge.entity.vo.ColumnVO
import com.fish.keXieOpenJudge.common.Result
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface ColumnService : IService<Column> {
    fun addWithTopic(insertColumnDTO: InsertColumnDTO): Result<*>
    fun getColumns(pageNo: Int, pageSize: Int): Result<Page<ColumnVO>>
    fun getColumn(columnId: Long): Result<ColumnVO>
    fun updateColumn(column: UpdateColumnDTO): Result<*>
    fun changeStatus(columnId: Long, action: Boolean): Result<*>
    fun deleteColumn(columnId: Long): Result<*>
}
