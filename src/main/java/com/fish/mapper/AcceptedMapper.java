package com.fish.mapper;

import com.fish.entity.pojo.Accepted;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 *  映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
public interface AcceptedMapper extends BaseMapper<Accepted> {
    @Select("select * from oj_accepted where user_id = #{userId}")
    ArrayList<Accepted> getMyAccepts(@Param("userID") String userId);
    @Select("select * from oj_accepted where topic_id = #{topicId}")
    ArrayList<Accepted> getTopicAccepts(@Param("topicId") Long topicId);
}
