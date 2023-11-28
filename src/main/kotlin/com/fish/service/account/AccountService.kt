package com.fish.service.account

import com.fish.common.Result
import com.fish.entity.dto.LoginAccountDTO
import com.fish.entity.dto.RegisterAccountDTO
import com.fish.entity.pojo.Account
import com.mybatisflex.core.service.IService

interface AccountService : IService<Account> {
    /**
     * 登陆服务，要求使用SpringSecurity实现
     * @param loginAccountDTO 登陆用户传输对象
     * @return 封装好的响应信息(包含token)
     */
    fun login(loginAccountDTO: LoginAccountDTO): Result<HashMap<String, Any>>

    /**
     * 注册服务，要求将敏感信息脱敏后再放入数据库(如密码、真名)
     * @param registerAccountDTO 注册用户传输对象
     * @return 封装好的响应信息
     */
    fun register(registerAccountDTO: RegisterAccountDTO): Result<*>

    /**
     * 修改用户信息服务，必须是已经登陆用户才可以使用(基于SpringSecurity实现)
     * @param account 用户信息
     * @param userId 要修改的用户的id
     * @return 封装好的响应信息
     */
    fun updateAccountInformation(account: Account, userId: String): Result<*>

    /**
     * 删除用户(假删除),要求在执行删除时检查执行者权限和被执行者权限，执行者权限不足则驳回操作
     * @param userId 要删除的用户id
     * @return 封装好的响应信息
     */
    fun deleteAccount(userId: String): Result<*>

    /**
     * 获取所有普通用户信息
     * @return 封装好的响应信息
     */
    fun getAccounts(): Result<ArrayList<Account>>

    /**
     * 获取所有管理员信息
     * @return 封装好的响应信息
     */
    fun getAdmins(): Result<ArrayList<Account>>

    /**
     * 更改用户角色服务，要求在执行时判断执行者是否有权赋予该角色
     * @param userId 要修改角色的用户id
     * @param roleId 要赋予的角色id
     * @return 封装好的响应信息
     */
    fun changeAccountRole(userId: String, roleId: Long): Result<*>
}
