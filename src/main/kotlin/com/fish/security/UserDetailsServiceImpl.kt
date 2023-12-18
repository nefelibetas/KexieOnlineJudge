package com.fish.security

import com.fish.entity.pojo.Account
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import com.fish.mapper.AccountMapper
import com.fish.mapper.RoleMapper
import jakarta.annotation.Resource
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
