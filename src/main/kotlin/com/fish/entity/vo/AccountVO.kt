package com.fish.entity.vo

import com.fish.entity.pojo.Account
import java.io.Serializable

class AccountVO(account: Account) : Serializable {
    var userId: String
    /**
     * 昵称
     */
    var nickname: String
    /**
     * 头像
     */
    var avatar: String
    /**
     * 性别
     */
    var gender: String
    /**
     * 专业
     */
    var specialty: String
    /**
     * 博客地址
     */
    var blogAddress: String
    /**
     * GitHub地址
     */
    var githubAddress: String
    init {
        userId = account.userId
        nickname = account.nickname
        avatar = account.avatar
        gender = account.gender
        specialty = account.specialty
        blogAddress = account.blogAddress
        githubAddress = account.githubAddress
    }
}
