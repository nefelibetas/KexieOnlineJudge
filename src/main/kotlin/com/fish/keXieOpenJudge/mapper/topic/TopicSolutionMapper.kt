package com.fish.keXieOpenJudge.mapper.topic

import com.fish.keXieOpenJudge.entity.dto.solution.InsertSolutionDTO
import com.fish.keXieOpenJudge.entity.pojo.topic.TopicSolution
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface TopicSolutionMapper : BaseMapper<TopicSolution> {
    @Insert("insert into oj_topic_solutions(`topic_id`, `title`, `content`) value (#{topicId}, #{title}, #{content})")
    fun addSolution(solution: InsertSolutionDTO) : Int
    @Select("select * from oj_topic_solutions where topic_id = #{topicId} and enabled = true and pined = true")
    fun selectPined(@Param("topicId") topicId: Long) : ArrayList<TopicSolution>
}
