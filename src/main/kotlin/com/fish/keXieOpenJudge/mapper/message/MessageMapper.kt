package com.fish.keXieOpenJudge.mapper.message

import com.fish.keXieOpenJudge.entity.pojo.message.Message
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Insert

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface MessageMapper : BaseMapper<Message> {
    @Insert("insert into oj_message(`type_id`, `title`, `content`, `send_to_user_id`, `notified`) value (#{typeId}, #{title}, #{content}, #{sendToUserId}, #{notified})")
    fun addMessage(message: Message): Int
}
