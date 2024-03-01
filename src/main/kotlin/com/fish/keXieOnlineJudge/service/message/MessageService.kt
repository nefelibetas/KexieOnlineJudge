package com.fish.keXieOnlineJudge.service.message

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.pojo.message.Message
import com.fish.keXieOnlineJudge.entity.vo.Announcement
import com.fish.keXieOnlineJudge.entity.vo.Notification
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface MessageService: IService<Message> {
    fun addMessage(message: Message): Result<*>
    fun getAnnouncement(pageNo: Int, pageSize: Int): Result<Page<Announcement>>
    fun getNotification(pageNo: Int, pageSize: Int): Result<Page<Notification>>
    fun getNotificationNumber(): Result<Int>
    fun changeMessageStatus(messageId: String): Result<*>
}