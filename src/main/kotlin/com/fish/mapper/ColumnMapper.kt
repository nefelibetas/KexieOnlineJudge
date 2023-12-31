package com.fish.mapper

import com.fish.entity.dto.InsertColumnDTO
import com.fish.entity.dto.UpdateColumnDTO
import com.fish.entity.pojo.Column
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Insert

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
