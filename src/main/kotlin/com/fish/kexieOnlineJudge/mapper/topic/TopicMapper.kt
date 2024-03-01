package com.fish.kexieOnlineJudge.mapper.topic

import com.fish.kexieOnlineJudge.entity.dto.topic.InsertTopicDTO
import com.fish.kexieOnlineJudge.entity.dto.topic.UpdateTopicDTO
import com.fish.kexieOnlineJudge.entity.pojo.topic.Topic
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
