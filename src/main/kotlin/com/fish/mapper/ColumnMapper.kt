package com.fish.mapper

import com.fish.entity.dto.ColumnDTO
import com.fish.entity.pojo.Column
import com.mybatisflex.core.BaseMapper

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface ColumnMapper: BaseMapper<Column> {
    fun updateInfo(column: ColumnDTO): Int
}
