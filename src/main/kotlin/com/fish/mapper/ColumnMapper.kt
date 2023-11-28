package com.fish.mapper

import com.fish.entity.pojo.Column
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface ColumnMapper : BaseMapper<Column> {
    @Update("update oj_column set enabled = false where column_id = #{columnId}")
    fun deleteColumn(@Param("columnId") columnId: Long): Int
}
