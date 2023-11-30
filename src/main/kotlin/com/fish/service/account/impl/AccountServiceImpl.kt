package com.fish.service.account.impl

import com.fish.common.Result
import com.fish.entity.dto.LoginAccountDTO
import com.fish.entity.dto.RegisterAccountDTO
import com.fish.entity.pojo.Account
import com.fish.entity.pojo.table.AccountTableDef.ACCOUNT
import com.fish.entity.vo.AccountVO
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import com.fish.mapper.AccountMapper
import com.fish.security.LoginAccount
import com.fish.service.account.AccountService
import com.fish.utils.JwtUtil
import com.fish.utils.RedisUtil
import com.fish.utils.ResultUtil.success
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.spring.service.impl.ServiceImpl
import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AccountServiceImpl : ServiceImpl<AccountMapper, Account>(), AccountService {
    @Resource
    private val authenticationManager: AuthenticationManager? = null

    @Resource
    private val passwordEncoder: PasswordEncoder? = null

    @Resource
    private val jwtUtil: JwtUtil? = null

    @Resource
    private val redisUtil: RedisUtil? = null

    @Value("\${jwt.year}")
    private val year: String? = null
    override fun login(loginAccountDTO: LoginAccountDTO): Result<HashMap<String, Any>> {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginAccountDTO.email, loginAccountDTO.password)
        val authentication = authenticationManager!!.authenticate(authenticationToken)
        if (Objects.isNull(authentication)) {
            throw ServiceException(ServiceExceptionEnum.ACCOUNT_NOT_FOUND)
        }
        val principal = authentication.principal as LoginAccount
        val redisKey = makeKey(principal.account.userId, principal.account.nickname)
        val token = jwtUtil!!.createToken(redisKey)
        val map = HashMap<String, Any>()
        map["Authorization"] = token
        map["account"] = AccountVO(principal.account)
        redisUtil!!.set(redisKey, principal)
        return success(map)
    }

    @Transactional
    override fun register(registerAccountDTO: RegisterAccountDTO): Result<*> {
        registerAccountDTO.password = passwordEncoder!!.encode(registerAccountDTO.password)
        val i = mapper!!.addAccount(registerAccountDTO)
        return if (i > 0) success<Any>()
        else throw ServiceException(ServiceExceptionEnum.ACCOUNT_EXISTED)
    }

    @Transactional
    override fun updateAccountInformation(account: Account, userId: String): Result<*> {
        if (account.roleId != null)
            throw ServiceException(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS)
        val credentials = SecurityContextHolder.getContext().authentication.credentials as String
        if (credentials != userId) throw ServiceException(ServiceExceptionEnum.AUTHENTICATION_FAILURE)
        val i = mapper!!.update(account)
        if (i > 0) return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun disableAccount(userId: String): Result<*> {
        if (checkRole(userId, null)) throw ServiceException(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS)
        val i = mapper!!.disableAccount(userId)
        return if (i > 0) success<Any>() else throw ServiceException(
            ServiceExceptionEnum.OPERATE_ERROR
        )
    }

    override fun getAccounts(): Result<ArrayList<Account>> {
        return success(mapper!!.getCustomAccounts())
    }
    override fun getAdmins(): Result<ArrayList<Account>> {
        return success(mapper!!.getAdmins())
    }

    @Transactional
    override fun changeAccountRole(userId: String, roleId: Long): Result<*> {
        if (checkRole(userId, roleId)) throw ServiceException(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS)
        val i = mapper!!.changeAccountRole(userId, roleId)
        return if (i > 0) success<Any>() else throw ServiceException(
            ServiceExceptionEnum.OPERATE_ERROR
        )
    }

    @Transactional
    override fun enableAccount(userId: String): Result<*> {
        val update = UpdateChain.of(ACCOUNT)
            .set(ACCOUNT.ENABLED, true)
            .where(ACCOUNT.USER_ID.eq(userId))
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    protected fun makeKey(userId: String?, nickname: String?): String {
        return nickname + "_" + userId + "_" + year
    }

    /**
     * <div>判断当前用户是否有足够权限</div>
     * @param userId 用户id
     * @param roleId 角色id
     * @return 是否有足够权限
     */
    protected fun checkRole(userId: String, roleId: Long?): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        val loginAccount = authentication.principal as LoginAccount
        val account = mapper!!.getAccount(userId)
        // 不能修改自己的角色
        if (loginAccount.account.userId == account.userId) throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        // roleId为空说明请求来自删除用户,仅需要判断执行者的权限是否足够删除
        // 否则说明请求来自修改角色，这时候直接判断执行者是否有权限赋予该角色
        val loginAccountRoleId = loginAccount.account.roleId!!
        if (!Objects.isNull(loginAccountRoleId))
            if (Objects.isNull(roleId) && !Objects.isNull(account.roleId!!)) loginAccountRoleId > account.roleId
            else loginAccountRoleId > roleId!!
        // 如果连登陆都没有也不放行
        return true
    }
}
