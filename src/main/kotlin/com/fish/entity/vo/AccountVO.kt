package com.fish.entity.vo

import com.fish.entity.pojo.Account
import java.io.Serializable

data class AccountVO(
    val userId: String?,
    /**
     * 昵称
     */
    val nickname: String?,
    /**
     * 头像
     */
    val avatar: String?,
    /**
     * 性别
     */
    val gender: String?,
    /**
     * 专业
     */
    val specialty: String?,
    /**
     * 邮箱
     */
    val email: String?,
    /**
     * 博客地址
     */
    val blogAddress: String?,
    /**
     * GitHub地址
     */
    val githubAddress: String?
) : Serializable {
    /**
     * 角色
     */
    var role: String? = null
    constructor(account: Account) : this(
        account.userId,
        account.nickname,
        account.avatar,
        account.gender,
        account.specialty,
        account.email,
        account.blogAddress,
        account.githubAddress
    ) {
        if (account.roleId == 3L)
            this.role = "普通用户"
        else
            this.role = "管理员"
    }
}
