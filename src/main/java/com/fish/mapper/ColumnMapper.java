package com.fish.mapper;

import com.fish.entity.pojo.Column;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 *  映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
public interface ColumnMapper extends BaseMapper<Column> {
    @Select("select * from oj_column where enabled = true and column_id = #{columnId}")
    Column getColumn(@Param("columnId")Long columnId);
    @Select("select * from oj_column where enabled = true")
    ArrayList<Column> getColumns();
    @Update("update oj_column set enabled = false where column_id = #{columnId}")
    int deleteColumn(@Param("columnId")Long columnId);
}
