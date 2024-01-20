package com.fish.keXieOpenJudge.service.like

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.pojo.topic.Like
import com.mybatisflex.core.service.IService

interface LikeService: IService<Like> {
    fun like(userId: String, beLikedId: Long?, occasion: String?, action: Boolean): Result<*>
}