package com.fish.kexieOnlineJudge.service.message.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.pojo.message.Message
import com.fish.kexieOnlineJudge.entity.pojo.message.table.MessageTableDef.MESSAGE
import com.fish.kexieOnlineJudge.entity.vo.Announcement
import com.fish.kexieOnlineJudge.entity.vo.Notification
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.message.MessageMapper
import com.fish.kexieOnlineJudge.service.message.MessageService
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
import com.fish.kexieOnlineJudge.utils.SecurityUtil
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl: ServiceImpl<MessageMapper, Message>(), MessageService {
    override fun addMessage(message: Message): Result<*> {
        if (isLogical(message)) {
            val i = mapper.addMessage(message)
            if (i > 0)
                return success<Any>()
        }
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    /**
     * 对于公告(1) -> 不允许有目标发送用户
     *
     * 对于通知(2) -> 必须要有目标发送用户
     *
     * 其他则为无效情况
     */
    private fun isLogical(message: Message): Boolean{
        val typeId = message.typeId?.toInt()
        when (typeId) {
            2 -> {
                message.sendToUserId?.let {
                    return true
                }
                return false
            }
            1 -> {
                message.notified = false
                message.sendToUserId?.let {
                    return false
                }
                return true
            }
            else -> throw ServiceException(ServiceExceptionEnum.SELECT_NOT_IN)
        }
    }
    /**
     * 公告
     */
    override fun getAnnouncement(pageNo: Int, pageSize: Int): Result<Page<Announcement>> {
        val wrapper = QueryWrapper.create()
            .select(MESSAGE.TITLE, MESSAGE.CONTENT, MESSAGE.SEND_TIME).from(MESSAGE)
            .where(MESSAGE.TYPE_ID.eq(1))
        val messageVOPage = mapper.paginateAs(Page(pageNo, pageSize), wrapper, Announcement::class.java)
        return success(messageVOPage)
    }
    /**
     * 个人通知
     */
    override fun getNotification(pageNo: Int, pageSize: Int): Result<Page<Notification>> {
        val id = SecurityUtil.getId()
        val wrapper = QueryWrapper.create()
            .select(MESSAGE.MESSAGE_ID, MESSAGE.TITLE, MESSAGE.CONTENT, MESSAGE.SEND_TIME, MESSAGE.NOTIFIED).from(MESSAGE)
            .where(MESSAGE.TYPE_ID.eq(2))
            .and(MESSAGE.SEND_TO_USER_ID.eq(id))
        val messageVOPage = mapper.paginateAs(Page(pageNo, pageSize), wrapper, Notification::class.java)
        return success(messageVOPage)
    }
    /**
     * 个人通知条数
     */
    override fun getNotificationNumber(): Result<Int> {
        val id = SecurityUtil.getId()
        val wrapper = QueryWrapper.create()
            .select().from(MESSAGE)
            .where(MESSAGE.TYPE_ID.eq(2))
            .and(MESSAGE.SEND_TO_USER_ID.eq(id))
            .and(MESSAGE.NOTIFIED.eq(true))
        val number = mapper.selectCountByQuery(wrapper)
        return success(number.toInt())
    }
    /**
     * 通知设置已读
     */
    override fun changeMessageStatus(messageId: String): Result<*> {
        val update = UpdateChain.of(Message::class.java)
            .set(Message::notified.column, false)
            .where(Message::messageId.eq(messageId))
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
}