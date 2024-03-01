package com.fish.kexieOnlineJudge.mapper.topic

import com.fish.kexieOnlineJudge.entity.dto.topic.InsertTopicSolutionCommentDTO
import com.fish.kexieOnlineJudge.entity.pojo.topic.TopicSolutionComment
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Insert

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface TopicSolutionCommentMapper : BaseMapper<TopicSolutionComment?> {
    @Insert("insert into oj_topic_solution_comment(user_id, solution_id, parent_id, comment_content) value (#{userId}, #{solutionId}, #{parentId}, #{commentContent})")
    fun addComment(insertTopicSolutionCommentDTO: InsertTopicSolutionCommentDTO): Int
}
