package com.fish.kexieOnlineJudge.service.like

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.pojo.topic.Like
import com.mybatisflex.core.service.IService

interface LikeService: IService<Like> {
    fun like(beLikedId: Long?, occasion: String?, action: Boolean): Result<*>
}