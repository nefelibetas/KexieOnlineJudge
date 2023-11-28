package com.fish.service.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.Account;
import com.fish.entity.vo.AccountVO;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.AccountMapper;
import com.fish.security.LoginAccount;
import com.fish.service.AccountService;
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
    public Result<HashMap<String,Object>> login(Account account) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword());
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
    public Result<?> register(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        int i = mapper.insert(account);
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
    /**
     * 删除前首先要确认执行删除的人是否有权限删除, ADMIN不能删除ROOT
     * @param userId 要删除的用户的id
     * @return 结果
     */
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
    /**
     * <div>先检查当前用户是否有权限修改权限信息再进行修改</div>
     * <div>
     *     比如：
     *      <div>1. ROOT 能修改 ADMIN、USER</div>
     *      <div>2. ADMIN 能修改 USER</div>
     *      <div>3. USER 则无修改权限</div>
     * </div>
     * @param userId 要修改的用户id
     * @param roleId 要修改成的角色id
     * @return 响应操作结果
     */
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
        if (loginAccount.getAccount().getUserId().equals(account.getUserId()))
            throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
        if(Objects.isNull(roleId)) {
            return loginAccount.getAccount().getRoleId() > account.getRoleId();
        } else {
            return loginAccount.getAccount().getRoleId() > roleId;
        }
    }
}
