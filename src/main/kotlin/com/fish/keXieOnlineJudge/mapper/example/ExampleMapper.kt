package com.fish.keXieOnlineJudge.mapper.example

import com.fish.keXieOnlineJudge.entity.dto.example.InsertExampleDTO
import com.fish.keXieOnlineJudge.entity.dto.example.UpdateExampleDTO
import com.fish.keXieOnlineJudge.entity.pojo.example.Example
import com.fish.keXieOnlineJudge.entity.vo.ExampleVO
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface ExampleMapper : BaseMapper<Example> {
    fun addExampleBatch(@Param("examples") exampleDTO: ArrayList<InsertExampleDTO>): Int
    fun updateExample(@Param("example") updateExampleDTO: UpdateExampleDTO): Int
    @Select("select * from oj_example where topic_id = #{topicId}")
    fun getExampleById(topicId: Long): List<Example>
    @Select("select oj_example.input, oj_example.output, oj_example_result.cpu_time, oj_example_result.real_time, oj_example_result.memory, oj_example_result.assessment_status " +
            "from oj_example inner join oj_example_result on oj_example.example_id = oj_example_result.example_id " +
            "where result_id = #{resultId}")
    fun getExampleVoByResultId(resultId: Long): ArrayList<ExampleVO>
}
