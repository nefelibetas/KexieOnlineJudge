package com.fish.keXieOpenJudge.mapper.example

import com.fish.keXieOpenJudge.entity.dto.example.InsertExampleDTO
import com.fish.keXieOpenJudge.entity.dto.example.UpdateExampleDTO
import com.fish.keXieOpenJudge.entity.pojo.example.Example
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
    fun updateExample(@Param("example") updateExampleDTO: UpdateExampleDTO): Int
}
