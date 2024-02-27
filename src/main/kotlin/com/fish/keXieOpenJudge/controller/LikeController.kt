package com.fish.keXieOpenJudge.controller

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.service.like.LikeService
import org.springframework.web.bind.annotation.PathVariable
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
    @PostMapping("/user/like/{userId}")
    fun like(
        @PathVariable userId: String,
        @RequestParam beLikedId: Long?,
        @RequestParam occasion: String?,
        @RequestParam(defaultValue = "true") action: Boolean
    ): Result<*> {
        return likeService.like(userId, beLikedId, occasion, action)
    }
}