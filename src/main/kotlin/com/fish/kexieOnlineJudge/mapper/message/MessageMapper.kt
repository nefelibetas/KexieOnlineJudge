package com.fish.kexieOnlineJudge.mapper.message

import com.fish.kexieOnlineJudge.entity.pojo.message.Message
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Update

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface MessageMapper : BaseMapper<Message> {
    @Insert("insert into oj_message(`type_id`, `title`, `content`, `send_to_user_id`) value (#{typeId}, #{title}, #{content}, #{sendToUserId})")
    fun addMessage(message: Message): Int
}
