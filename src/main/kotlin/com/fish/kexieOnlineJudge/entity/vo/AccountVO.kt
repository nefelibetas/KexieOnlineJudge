package com.fish.kexieOnlineJudge.entity.vo

import com.fish.kexieOnlineJudge.entity.pojo.account.Account
import java.io.Serializable

class AccountVO(): Serializable {
    var userId: String? = null
    /**
     * 昵称
     */
    var nickname: String? = null
    /**
     * 头像
     */
    var avatar: String? = null
    /**
     * 性别
     */
    var gender: String? = null
    /**
     * 专业
     */
    var specialty: String? = null
    /**
     * 邮箱
     */
    var email: String? = null
    /**
     * 博客地址
     */
    var blogAddress: String? = null
    /**
     * GitHub地址
     */
    var githubAddress: String? = null
    /**
     * 角色
     */
    var roleId: String? = null
    constructor(account: Account) : this() {
        this.userId = account.userId
        this.email = account.email
        this.blogAddress = account.blogAddress
        this.specialty = account.specialty
        this.avatar = account.avatar
        this.gender = account.gender
        this.githubAddress = account.githubAddress
        this.nickname = account.nickname
        this.roleId = if (account.roleId?.toInt() == 1) "管理员" else "普通用户"
    }
}
