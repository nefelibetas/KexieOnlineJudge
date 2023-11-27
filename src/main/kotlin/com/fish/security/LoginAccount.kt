package com.fish.security

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fish.entity.pojo.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * 实现了**UserDetails**的用户类, SpringSecurity认证时使用
 * 一些属性在序列化时会出错, 因此加上@JsonIgnoreProperties注解消除, 并且需要自己实现权限(见SystemAuthority)
 * @author fish
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class LoginAccount @JsonCreator constructor(
    @JsonProperty("account") val account: Account,
    @JsonProperty("authorities") val grantedAuthorities: ArrayList<SystemAuthority?>
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return grantedAuthorities
    }
    override fun getPassword(): String {
        return account.password!!
    }
    override fun getUsername(): String {
        return account.email!!
    }
    override fun isAccountNonExpired(): Boolean {
        return true
    }
    override fun isAccountNonLocked(): Boolean {
        return true
    }
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }
    override fun isEnabled(): Boolean {
        return account.enabled!!
    }
}
