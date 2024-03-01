package com.fish.keXieOnlineJudge.controller

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.service.like.LikeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author BridgeFish
 * <br/>
 * 点赞相关的控制器
 */

@RestController
class LikeController(val likeService: LikeService) {
    @PostMapping("/user/like")
    fun like(
        @RequestParam beLikedId: Long?,
        @RequestParam occasion: String?,
        @RequestParam(defaultValue = "true") action: Boolean
    ): Result<*> {
        return likeService.like(beLikedId, occasion, action)
    }
}