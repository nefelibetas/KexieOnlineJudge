package com.fish.kexieOnlineJudge.mapper.topic

import com.fish.kexieOnlineJudge.entity.dto.topic.InsertTopicSolutionDTO
import com.fish.kexieOnlineJudge.entity.pojo.topic.TopicSolution
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Insert

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface TopicSolutionMapper : BaseMapper<TopicSolution> {
    @Insert("insert into oj_topic_solution(`topic_id`, `title`, `solution_content`) value (#{topicId}, #{title}, #{solutionContent})")
    fun addSolution(solution: InsertTopicSolutionDTO) : Int
}
