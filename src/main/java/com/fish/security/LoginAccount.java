package com.fish.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fish.entity.pojo.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <div>实现了<strong>UserDetails</strong>的用户类, SpringSecurity认证时使用</div>
 * <div>一些属性在序列化时会出错, 因此加上@JsonIgnoreProperties注解消除, 并且需要自己实现权限(见SystemAuthority)</div>
 * @author fish
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginAccount implements UserDetails {
    @Getter
    private final Account account;
    private final ArrayList<SystemAuthority> grantedAuthorities;
    @JsonCreator
    public LoginAccount(@JsonProperty("account")Account account, @JsonProperty("authorities")ArrayList<SystemAuthority> grantedAuthorities) {
        this.account = account;
        this.grantedAuthorities = grantedAuthorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return account.getPassword();
    }
    @Override
    public String getUsername() {
        return account.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return account.getEnabled();
    }
}
