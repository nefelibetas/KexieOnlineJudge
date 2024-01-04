package com.fish.keXieOpenJudge.service.account.impl

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.account.LoginAccountDTO
import com.fish.keXieOpenJudge.entity.dto.account.PasswordDTO
import com.fish.keXieOpenJudge.entity.dto.account.RegisterAccountDTO
import com.fish.keXieOpenJudge.entity.dto.account.UpdateAccountDTO
import com.fish.keXieOpenJudge.entity.pojo.account.Account
import com.fish.keXieOpenJudge.entity.pojo.account.table.AccountTableDef.ACCOUNT
import com.fish.keXieOpenJudge.entity.vo.AccountVO
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.account.AccountMapper
import com.fish.keXieOpenJudge.security.LoginAccount
import com.fish.keXieOpenJudge.service.account.AccountService
import com.fish.keXieOpenJudge.utils.RedisUtil
import com.fish.keXieOpenJudge.utils.ResultUtil.success
import com.fish.keXieOpenJudge.utils.SecurityUtil
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AccountServiceImpl(
    val authenticationManager: AuthenticationManager,
    val passwordEncoder: PasswordEncoder,
    val jwtUtil: com.fish.keXieOpenJudge.utils.JwtUtil,
    val redisUtil: RedisUtil
) : ServiceImpl<AccountMapper, Account>(), AccountService {
    @Value("\${jwt.year}")
    private val year: String? = null
    override fun login(loginAccountDTO: LoginAccountDTO): Result<HashMap<String, Any>> {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginAccountDTO.email, loginAccountDTO.password)
        val authentication = authenticationManager.authenticate(authenticationToken)
        if (Objects.isNull(authentication)) {
            throw ServiceException(ServiceExceptionEnum.ACCOUNT_NOT_FOUND)
        }
        val principal = authentication.principal as LoginAccount
        val redisKey = makeKey(principal.account.userId!!, principal.account.nickname!!)
        val token = jwtUtil.createToken(redisKey)
        val map = HashMap<String, Any>()
        map["Authorization"] = token
        map["account"] = AccountVO(principal.account)
        redisUtil.set(redisKey, principal)
        return success(map)
    }

    @Transactional
    override fun register(registerAccountDTO: RegisterAccountDTO): Result<*> {
        registerAccountDTO.password = passwordEncoder.encode(registerAccountDTO.password)
        mapper!!.addAccount(registerAccountDTO)
        return success<Any>()
    }

    @Transactional
    override fun updateAccountInformation(updateAccountDTO: UpdateAccountDTO): Result<*> {
        val id = SecurityUtil.getId()
        updateAccountDTO.userId = id
        val i = mapper!!.updateAccount(updateAccountDTO)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    override fun getAccounts(pageNo: Int, pageSize: Int): Result<Page<Account>> {
        val wrapper = QueryWrapper.create().select(ACCOUNT.USER_ID, ACCOUNT.ROLE_ID, ACCOUNT.AVATAR, ACCOUNT.USERNAME,
                ACCOUNT.STUDENT_ID, ACCOUNT.GENDER, ACCOUNT.SPECIALTY, ACCOUNT.NICKNAME,
                ACCOUNT.EMAIL, ACCOUNT.QQ, ACCOUNT.GITHUB_ADDRESS, ACCOUNT.BLOG_ADDRESS, ACCOUNT.ENABLED)
            .from(ACCOUNT)
            .where(ACCOUNT.ENABLED.eq(true)).and(ACCOUNT.ROLE_ID.eq(3))
        val accountPage = mapper.paginate(Page.of(pageNo, pageSize), wrapper)
        return success(accountPage)
    }
    override fun getAdmins(pageNo: Int, pageSize: Int): Result<Page<Account>> {
        val wrapper = QueryWrapper.create().select(ACCOUNT.USER_ID, ACCOUNT.ROLE_ID, ACCOUNT.AVATAR, ACCOUNT.USERNAME,
                ACCOUNT.STUDENT_ID, ACCOUNT.GENDER, ACCOUNT.SPECIALTY, ACCOUNT.NICKNAME,
                ACCOUNT.EMAIL, ACCOUNT.QQ, ACCOUNT.GITHUB_ADDRESS, ACCOUNT.BLOG_ADDRESS, ACCOUNT.ENABLED)
            .from(ACCOUNT)
            .where(ACCOUNT.ENABLED.eq(true)).and(ACCOUNT.ROLE_ID.lt(3))
        val accountPage = mapper.paginate(Page.of(pageNo, pageSize), wrapper)
        return success(accountPage)
    }

    @Transactional
    override fun changeAccountRole(userId: String, roleId: Long): Result<*> {
        if (roleId == 1L)
            throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        if (checkRole(userId, roleId))
            throw ServiceException(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS)
        val i = mapper!!.changeAccountRole(userId, roleId)
        return if (i > 0) success<Any>() else throw ServiceException(
            ServiceExceptionEnum.OPERATE_ERROR
        )
    }

    @Transactional
    override fun changeStatus(userId: String, action: Boolean): Result<*> {
        val account = mapper.selectOneById(userId)
        val loginAccount = SecurityContextHolder.getContext().authentication.principal as LoginAccount
        if (loginAccount.account.roleId!! > account.roleId!!)
            throw ServiceException(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS)
        val update = UpdateChain.of(Account::class.java)
            .set(Account::enabled.column, action)
            .where(Account::userId eq userId)
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun changePassword(passwordDTO: PasswordDTO): Result<*> {
        val id = SecurityUtil.getId()
        val account = mapper.getAccount(id)
        val matches = passwordEncoder.matches(passwordDTO.oldPwd!!, account.password)
        if (matches) {
            val update = UpdateChain.of(Account::class.java)
                .set(Account::password.column, passwordEncoder.encode(passwordDTO.newPwd)).where(Account::userId eq id).update()
            if (update) {
                redisUtil.remove(makeKey(id, account.nickname!!))
                return success<Any>()
            }
        }
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    protected fun makeKey(userId: String, nickname: String): String {
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
        val id = authentication.credentials as String
        val loginAccount = authentication.principal as LoginAccount
        if (id == userId)
            throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
        val loginAccountRoleId = loginAccount.account.roleId!!
        if (!Objects.isNull(loginAccountRoleId)) {
            val account = mapper!!.getAccount(userId)
            return if (Objects.isNull(roleId) && !Objects.isNull(account.roleId!!))
                loginAccountRoleId > account.roleId
            else
                loginAccountRoleId > roleId!!
        }
        return true
    }
}
