package com.fish.kexieOnlineJudge.security

import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.account.AccountMapper
import com.fish.kexieOnlineJudge.mapper.account.RoleMapper
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
        val account = accountMapper.getAccountByEmail(username)
        account?.let {
            return LoginAccount(account, getRoles(account.roleId!!))
        }
        throw ServiceException(ServiceExceptionEnum.ACCOUNT_NOT_FOUND)
    }
    private fun getRoles(roleId: Long): ArrayList<SystemAuthority?> {
        val role = roleMapper.getRoleById(roleId)
        val simpleGrantedAuthority = SystemAuthority(role.roleName!!)
        val authorities = ArrayList<SystemAuthority?>()
        authorities.add(simpleGrantedAuthority)
        return authorities
    }
}
