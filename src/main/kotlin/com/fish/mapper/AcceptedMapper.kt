package com.fish.mapper

import com.fish.entity.pojo.Accepted
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface AcceptedMapper : BaseMapper<Accepted> {
    @Select("select * from oj_accepted where user_id = #{userId}")
    fun getMyAccepts(@Param("userID") userId: String): ArrayList<Accepted>

    @Select("select * from oj_accepted where topic_id = #{topicId}")
    fun getTopicAccepts(@Param("topicId") topicId: Long): ArrayList<Accepted>
}
