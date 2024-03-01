package com.fish.keXieOpenJudge.controller

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.pojo.message.Message
import com.fish.keXieOpenJudge.entity.vo.Announcement
import com.fish.keXieOpenJudge.entity.vo.Notification
import com.fish.keXieOpenJudge.service.message.MessageService
import com.mybatisflex.core.paginate.Page
import org.springframework.web.bind.annotation.*

@RestController
class MessageController(val messageService: MessageService) {
    @GetMapping("/message/announcement")
    fun getAnnouncement(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "20") pageSize: Int
    ): Result<Page<Announcement>> = messageService.getAnnouncement(pageNo, pageSize)
    @GetMapping("/user/message/notification")
    fun getNotification(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "20") pageSize: Int
    ): Result<Page<Notification>> = messageService.getNotification(pageNo, pageSize)
    @GetMapping("/user/message/notificationNumber")
    fun getNotificationNumber(): Result<Int> = messageService.getNotificationNumber()
    @PostMapping("/admin/message")
    fun addMessage(@RequestBody message: Message): Result<*> = messageService.addMessage(message)
    @PutMapping("/user/message/{messageId}")
    fun changeMessageStatus(@PathVariable messageId: String): Result<*> = messageService.changeMessageStatus(messageId)

}