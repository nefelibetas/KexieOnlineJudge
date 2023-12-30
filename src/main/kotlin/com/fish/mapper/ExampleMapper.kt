package com.fish.mapper

import com.fish.entity.dto.InsertExampleDTO
import com.fish.entity.pojo.Example
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Param

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface ExampleMapper : BaseMapper<Example?> {
    fun addExampleBatch(@Param("examples") exampleDTO: ArrayList<InsertExampleDTO>): Int
}
