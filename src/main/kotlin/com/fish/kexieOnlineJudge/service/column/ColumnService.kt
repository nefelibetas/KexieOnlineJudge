package com.fish.kexieOnlineJudge.service.column

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.column.InsertColumnDTO
import com.fish.kexieOnlineJudge.entity.dto.column.UpdateColumnDTO
import com.fish.kexieOnlineJudge.entity.pojo.column.Column
import com.fish.kexieOnlineJudge.entity.vo.ColumnVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface ColumnService : IService<Column> {
    fun addWithTopic(insertColumnDTO: InsertColumnDTO): Result<*>
    fun getColumns(pageNo: Int, pageSize: Int): Result<Page<ColumnVO>>
    fun getColumn(columnId: Long): Result<ColumnVO>
    fun updateColumn(column: UpdateColumnDTO): Result<*>
    fun changeStatus(columnId: Long, action: Boolean): Result<*>
    fun deleteColumn(columnId: Long): Result<*>
    fun search(keyword: String, pageNo: Int, pageSize: Int): Result<Page<ColumnVO>>
}
