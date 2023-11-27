package com.fish.service.account.impl;

import com.fish.common.Result;
import com.fish.entity.dto.LoginAccountDTO;
import com.fish.entity.dto.RegisterAccountDTO;
import com.fish.entity.pojo.Account;
import com.fish.entity.vo.AccountVO;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.AccountMapper;
import com.fish.security.LoginAccount;
import com.fish.service.account.AccountService;
import com.fish.utils.JwtUtil;
import com.fish.utils.RedisUtil;
import com.fish.utils.ResultUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private RedisUtil redisUtil;
    @Value("${jwt.year}")
    private String year;
    @Override
    public Result<HashMap<String,Object>> login(LoginAccountDTO loginAccountDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginAccountDTO.getEmail(), loginAccountDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new ServiceException(ServiceExceptionEnum.ACCOUNT_NOT_FOUND);
        }
        LoginAccount principal = (LoginAccount) authentication.getPrincipal();
        String redisKey = makeKey(principal.getAccount().getUserId(), principal.getAccount().getNickname());
        String token = jwtUtil.createToken(redisKey);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("accountInfo", new AccountVO(principal.getAccount()));
        redisUtil.set(redisKey, principal);
        return ResultUtil.success(map);
    }
    @Transactional
    @Override
    public Result<?> register(RegisterAccountDTO registerAccountDTO) {
        registerAccountDTO.setPassword(passwordEncoder.encode(registerAccountDTO.getPassword()));
        int i = mapper.addAccount(registerAccountDTO);
        if (i > 0)
            return ResultUtil.success();
        else
            throw new ServiceException(ServiceExceptionEnum.ACCOUNT_EXISTED);
    }
    @Transactional
    @Override
    public Result<?> updateAccountInformation(Account account, String userId) {
        String credentials = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        if (!credentials.equals(userId))
            throw new ServiceException(ServiceExceptionEnum.AUTHENTICATION_FAILURE);
        int i = mapper.update(account);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> deleteAccount(String userId) {
        if (checkRole(userId, null))
            throw new ServiceException(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS);
        int i = mapper.deleteAccount(userId);
        if (i > 0)
            return ResultUtil.success();
        else
            throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Override
    public Result<ArrayList<Account>> getAccounts() {
        return ResultUtil.success(mapper.getCustomAccounts());
    }
    @Override
    public Result<ArrayList<Account>> getAdmins() {
        return ResultUtil.success(mapper.getAdmins());
    }
    @Transactional
    @Override
    public Result<?> changeAccountRole(String userId, Long roleId) {
        if (checkRole(userId, roleId))
            throw new ServiceException(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS);
        int i = mapper.changeAccountRole(userId, roleId);
        if (i > 0)
            return ResultUtil.success();
        else
            throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    protected String makeKey(String userId, String nickname) {
        return nickname + "_" + userId + "_" + year;
    }
    /**
     * <div>判断当前用户是否有足够权限</div>
     * @param userId 用户id
     * @param roleId 角色id
     * @return 是否有足够权限
     */
    protected boolean checkRole(String userId, Long roleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginAccount loginAccount = (LoginAccount) authentication.getPrincipal();
        Account account = mapper.getAccount(userId);
        // 不能修改自己的角色
        if (Objects.equals(loginAccount.getAccount().getUserId(), account.getUserId()))
            throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
        // roleId为空说明请求来自删除用户,仅需要判断执行者的权限是否足够删除
        // 否则说明请求来自修改角色，这时候直接判断执行者是否有权限赋予该角色
        Long loginAccountRoleId = loginAccount.getAccount().getRoleId();
        if (!Objects.isNull(loginAccountRoleId))
            if(Objects.isNull(roleId) && !Objects.isNull(account.getRoleId())) {
                return loginAccountRoleId > account.getRoleId();
            } else {
                return loginAccountRoleId > roleId;
            }
        // 如果连登陆都没有也不放行
        return true;
    }
}
