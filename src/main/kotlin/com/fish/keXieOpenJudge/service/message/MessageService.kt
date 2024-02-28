package com.fish.keXieOpenJudge.service.message

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.pojo.message.Message
import com.fish.keXieOpenJudge.entity.vo.Announcement
import com.fish.keXieOpenJudge.entity.vo.Notification
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface MessageService: IService<Message> {
    fun addMessage(message: Message): Result<*>
    fun getAnnouncement(pageNo: Int, pageSize: Int): Result<Page<Announcement>>
    fun getNotification(pageNo: Int, pageSize: Int): Result<Page<Notification>>
    fun getNotificationNumber(): Result<Int>
    fun changeMessageStatus(messageId: String): Result<*>
}