package com.fish.kexieOnlineJudge.service.like.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.pojo.topic.Like
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.LikeTableDef.LIKE
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.topic.LikeMapper
import com.fish.kexieOnlineJudge.service.like.LikeService
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
import com.fish.kexieOnlineJudge.utils.SecurityUtil
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl: ServiceImpl<LikeMapper, Like>(), LikeService {
    override fun like(beLikedId: Long?, occasion: String?, action: Boolean): Result<*> {
        beLikedId?.let {
            occasion?.let {
                val userId = SecurityUtil.getId()
                if (action)
                    return doTrue(userId, beLikedId, occasion)
                else
                    return doFalse(userId, beLikedId, occasion)
            }
        }
        throw ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT)
    }

    /**
     * 点赞操作
     */
    private fun doTrue(userId: String, beLikedId: Long?, occasion: String): Result<*> {
        val like = makeEntity(userId, beLikedId, occasion)
        val i = mapper.insert(like)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
    /**
     * 取消点赞操作
     */
    private fun doFalse(userId: String, beLikedId: Long?, occasion: String): Result<*>{
        val wrapper = makeQueryWrapper(userId, beLikedId, occasion)
        val i = mapper.deleteByQuery(wrapper)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }
    /**
     * 构造实例返回
     */
    private fun makeEntity(userId: String, beLikedId: Long?, occasion: String): Like{
        return when (occasion) {
            Occasion.COMMENT -> Like(userId = userId, commentId = beLikedId, solutionId = null)
            Occasion.SOLUTION -> Like(userId = userId, solutionId = beLikedId, commentId = null)
            else -> throw ServiceException(ServiceExceptionEnum.SELECT_NOT_IN)
        }
    }
    private fun makeQueryWrapper(userId: String, beLikedId: Long?, occasion: String): QueryWrapper {
        return when(occasion) {
            Occasion.COMMENT -> QueryWrapper().from(LIKE).where(LIKE.USER_ID.eq(userId)).and(LIKE.COMMENT_ID.eq(beLikedId))
            Occasion.SOLUTION -> QueryWrapper().from(LIKE).where(LIKE.USER_ID.eq(userId)).and(LIKE.SOLUTION_ID.eq(beLikedId))
            else -> throw ServiceException(ServiceExceptionEnum.SELECT_NOT_IN)
        }
    }
}
private object Occasion {
    const val COMMENT: String = "评论"
    const val SOLUTION: String = "题解"
}