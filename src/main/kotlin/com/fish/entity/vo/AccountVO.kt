package com.fish.entity.vo

import com.fish.entity.pojo.Account
import java.io.Serializable

class AccountVO(account: Account) : Serializable {
    var userId: String?
    /**
     * 昵称
     */
    private var nickname: String
    /**
     * 头像
     */
    private var avatar: String
    /**
     * 性别
     */
    private var gender: String
    /**
     * 专业
     */
    private var specialty: String
    /**
     * 博客地址
     */
    private var blogAddress: String
    /**
     * GitHub地址
     */
    private var githubAddress: String
    init {
        userId = account.userId
        nickname = account.nickname!!
        avatar = account.avatar!!
        gender = account.gender!!
        specialty = account.specialty!!
        blogAddress = account.blogAddress!!
        githubAddress = account.githubAddress!!
    }
}
