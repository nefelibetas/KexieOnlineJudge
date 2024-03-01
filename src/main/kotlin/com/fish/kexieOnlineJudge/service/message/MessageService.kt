package com.fish.kexieOnlineJudge.service.message

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.pojo.message.Message
import com.fish.kexieOnlineJudge.entity.vo.Announcement
import com.fish.kexieOnlineJudge.entity.vo.Notification
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface MessageService: IService<Message> {
    fun addMessage(message: Message): Result<*>
    fun getAnnouncement(pageNo: Int, pageSize: Int): Result<Page<Announcement>>
    fun getNotification(pageNo: Int, pageSize: Int): Result<Page<Notification>>
    fun getNotificationNumber(): Result<Int>
    fun changeMessageStatus(messageId: String): Result<*>
}