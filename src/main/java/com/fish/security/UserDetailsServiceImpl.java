package com.fish.security;

import com.fish.entity.pojo.Account;
import com.fish.entity.pojo.Role;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.AccountMapper;
import com.fish.mapper.RoleMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.getAccountByEmail(username);
        if (Objects.isNull(account))
            throw new ServiceException(ServiceExceptionEnum.ACCOUNT_NOT_FOUND);
        return new LoginAccount(account, getRoles(account.getRoleId()));
    }
    protected ArrayList<SystemAuthority> getRoles(Long roleId) {
        Role role = roleMapper.getRoleById(roleId);
        SystemAuthority simpleGrantedAuthority = new SystemAuthority(role.getRoleName());
        ArrayList<SystemAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }
}
