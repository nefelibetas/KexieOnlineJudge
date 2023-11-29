package com.fish.service.column

import com.fish.common.Result
import com.fish.entity.pojo.Column
import com.fish.entity.vo.ColumnVO
import com.mybatisflex.core.service.IService
import jakarta.validation.Valid

interface ColumnService : IService<Column> {
    fun addColumn(column: Column): Result<*>
    fun addColumnBatch(columns: @Valid ArrayList<Column>): Result<*>
    fun getColumns(): Result<ArrayList<ColumnVO>>
    fun getColumn(columnId: Long): Result<ColumnVO>
    fun updateColumn(column: Column): Result<*>
    fun disableColumn(columnId: Long): Result<*>
    fun enableColumn(columnId: Long): Result<*>
    /**
     * 删除特定标签，删除前需要root权限。<br></br>
     * 还需要在关联表的记录
     * @param columnId 栏目id
     * @return 封装好的响应信息
     */
    fun deleteColumn(columnId: Long): Result<*>
}
