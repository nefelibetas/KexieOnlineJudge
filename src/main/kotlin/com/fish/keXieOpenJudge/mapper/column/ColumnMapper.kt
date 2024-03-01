package com.fish.keXieOpenJudge.mapper.column

import com.fish.keXieOpenJudge.entity.dto.column.InsertColumnDTO
import com.fish.keXieOpenJudge.entity.dto.column.UpdateColumnDTO
import com.fish.keXieOpenJudge.entity.pojo.column.Column
import com.mybatisflex.core.BaseMapper

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface ColumnMapper: BaseMapper<Column> {
    fun updateInfo(column: UpdateColumnDTO): Int
    fun insertColumn(insertColumnDTO: InsertColumnDTO): Int
}
