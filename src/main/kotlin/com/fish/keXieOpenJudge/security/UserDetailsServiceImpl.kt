package com.fish.keXieOpenJudge.security

import com.fish.keXieOpenJudge.entity.pojo.account.Account
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import com.fish.keXieOpenJudge.mapper.account.AccountMapper
import com.fish.keXieOpenJudge.mapper.account.RoleMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsServiceImpl(
    val accountMapper: AccountMapper,
    val roleMapper: RoleMapper
) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val account : Account = accountMapper.getAccountByEmail(username)
        if (Objects.isNull(account))
            throw ServiceException(ServiceExceptionEnum.ACCOUNT_NOT_FOUND)
        return LoginAccount(account, getRoles(account.roleId!!))
    }
    private fun getRoles(roleId: Long): ArrayList<SystemAuthority?> {
        val role = roleMapper.getRoleById(roleId)
        val simpleGrantedAuthority = SystemAuthority(role.roleName!!)
        val authorities = ArrayList<SystemAuthority?>()
        authorities.add(simpleGrantedAuthority)
        return authorities
    }
}
