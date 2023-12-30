package com.fish.mapper

import com.fish.entity.dto.InsertTopicDTO
import com.fish.entity.dto.UpdateTopicDTO
import com.fish.entity.pojo.Topic
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Param

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface TopicMapper : BaseMapper<Topic> {
    fun addTopic(insertTopicDTO: InsertTopicDTO): Int
    fun updateTopic(@Param("topicId")topicId: Long, @Param("updateTopic")updateTopicDTO: UpdateTopicDTO): Int
}
