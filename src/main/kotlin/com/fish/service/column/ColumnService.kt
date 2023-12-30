package com.fish.service.column

import com.fish.common.Result
import com.fish.entity.dto.ColumnDTO
import com.fish.entity.pojo.Column
import com.fish.entity.vo.ColumnVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface ColumnService : IService<Column> {
    fun addColumn(column: Column): Result<*>
    fun getColumns(pageNo: Int, pageSize: Int): Result<Page<ColumnVO>>
    fun getColumn(columnId: Long): Result<ColumnVO>
    fun updateColumn(column: ColumnDTO): Result<*>
    fun changeStatus(columnId: Long, action: Boolean): Result<*>
    /**
     * 删除特定标签，删除前需要root权限。<br></br>
     * 还需要在关联表的记录
     * @param columnId 栏目id
     * @return 封装好的响应信息
     */
    fun deleteColumn(columnId: Long): Result<*>
}
