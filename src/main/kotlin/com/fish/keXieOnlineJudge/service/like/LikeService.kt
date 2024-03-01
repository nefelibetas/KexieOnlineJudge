package com.fish.keXieOnlineJudge.service.like

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.pojo.topic.Like
import com.mybatisflex.core.service.IService

interface LikeService: IService<Like> {
    fun like(beLikedId: Long?, occasion: String?, action: Boolean): Result<*>
}