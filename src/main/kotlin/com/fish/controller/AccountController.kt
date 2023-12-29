package com.fish.controller

import com.fish.common.Result
import com.fish.entity.dto.LoginAccountDTO
import com.fish.entity.dto.RegisterAccountDTO
import com.fish.entity.dto.UpdateAccountDTO
import com.fish.entity.pojo.Account
import com.fish.service.account.AccountService
import com.mybatisflex.core.paginate.Page
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.*

@RestController
class AccountController(val accountService: AccountService) {
    /**
     * 登陆接口
     * @param loginAccountDTO 专用于登录的用户传输对象，特点在于内部只有且只校验邮箱和密码是否有效
     * @return 用户基本信息(不包含敏感信息)和token,后续访问接口需要携带token
     */
    @PostMapping("/login")
    fun login(@RequestBody loginAccountDTO: @Valid LoginAccountDTO?): Result<HashMap<String, Any>> {
        return accountService.login(loginAccountDTO!!)
    }

    /**
     * 注册接口
     * @param registerAccountDTO 必填email、password和nickname(并且校验是否为空),其他作为选填。
     * @return 响应code为200表示注册成功，否则响应其他信息
     */
    @PostMapping("/register")
    fun register(@RequestBody registerAccountDTO: @Valid RegisterAccountDTO?): Result<*> {
        return accountService.register(registerAccountDTO!!)
    }

    /**
     * 更新用户信息,由用户自己发出才有效
     * @return 响应code为200表示请求成功
     */
    @PutMapping("/user/update")
    fun updateAccountInformation(@RequestBody updateAccountDTO: @Valid UpdateAccountDTO): Result<*> {
        return accountService.updateAccountInformation(updateAccountDTO)
    }

    /**
     * 删除用户接口(假删除)
     * @param userId 要删除的用户id
     * @return 响应code为200表示请求成功
     */
    @PutMapping("/admin/disable/{userId}")
    fun deleteAccount(@PathVariable userId: String?): Result<*> {
        return accountService.disableAccount(userId!!)
    }
    @PutMapping("/admin/enable/{userId}")
    fun enableAccount(@PathVariable userId: String?): Result<*>{
        return accountService.enableAccount(userId!!)
    }

    /**
     *
     * 获取所用用户
     * @param operate 获取普通用户或管理员通过该字段值决定
     * @return 用户信息,含敏感信息。仅开放给管理员
     */
    @GetMapping("/admin/gets")
    fun getAccounts(
        @RequestParam(defaultValue = "0", required = true) operate: @Pattern(regexp = "[01]") String?,
        @RequestParam(defaultValue = "1", required = false) pageNo: Int?,
        @RequestParam(defaultValue = "20", required = false) pageSize: Int?
    ): Result<Page<Account>> {
        if (operate == "0")
            return accountService.getAccounts(pageNo!!, pageSize!!)
        return accountService.getAdmins(pageNo!!, pageSize!!)
    }

    /**
     *
     * 切换用户身份
     * @param userId 要切换的用户id
     * @param roleId 预期的身份id
     * @return 响应code为200表示请求成功
     */
    @PutMapping("/admin/changeRole/{userId}/{roleId}")
    fun changeAccountRole(
        @PathVariable userId: String?,
        @PathVariable roleId: Long?
    ): Result<*> {
        return accountService.changeAccountRole(userId!!, roleId!!)
    }
}
