package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.dto.LoginAccountDTO;
import com.fish.entity.dto.RegisterAccountDTO;
import com.fish.entity.pojo.Account;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.service.account.AccountService;
import com.fish.utils.ResultUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;
    /**
     * <p>登陆接口</p>
     * @param loginAccountDTO 专用于登录的用户传输对象，特点在于内部只有且只校验邮箱和密码是否有效
     * @return 用户基本信息(不包含敏感信息)和token,后续访问接口需要携带token
     */
    @PostMapping("/login")
    public Result<HashMap<String,Object>> login(@Valid @RequestBody LoginAccountDTO loginAccountDTO) {
        return accountService.login(loginAccountDTO);
    }
    /**
     * <p>注册接口</p>
     * @param registerAccountDTO 必填email、password和nickname(并且校验是否为空)，其他作为选填。
     * @return 响应code为200表示注册成功，否则响应其他信息
     */
    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody RegisterAccountDTO registerAccountDTO) {
        return accountService.register(registerAccountDTO);
    }
    /**
     * <p>更新用户信息,由用户自己发出才有效</p>
     * @param account 用户要修改的信息存放在内,其中account的id非必填，必须要登录后才能修改
     * @param userId 用户id, 还需要和实体类中的id作对比.
     * @return 响应code为200表示请求成功
     */
    @PutMapping("/user/update/{userId}")
    public Result<?> updateAccountInformation(
            @Valid @RequestBody Account account,
            @PathVariable @NotBlank(message = "用户Id不能为空") String userId) {
        return accountService.updateAccountInformation(account, userId);
    }

    /**
     * <p>删除用户接口(假删除)</p>
     * @param userId 要删除的用户id
     * @return 响应code为200表示请求成功
     */
    @DeleteMapping("/admin/delete/{userId}")
    public Result<?> deleteAccount(@PathVariable @NotBlank(message = "用户Id未填写") String userId){
        return accountService.deleteAccount(userId);
    }

    /**
     * <p>获取所用用户</p>
     * @param operate 获取普通用户或管理员通过该字段值决定
     * @return 用户信息,含敏感信息。仅开放给管理员
     */
    @GetMapping("/admin/gets/{operate}")
    public Result<ArrayList<Account>> getAccounts(@PathVariable @Pattern(regexp = "^([12])$", message = "只能从1和2中选择") String operate) {
        return switch (operate) {
            case "1" -> accountService.getAccounts();
            case "2" -> accountService.getAdmins();
            default -> ResultUtil.failure(ServiceExceptionEnum.OPERATE_ERROR);
        };
    }
    /**
     * <p>切换用户身份</p>
     * @param userId 要切换的用户id
     * @param roleId 预期的身份id
     * @return 响应code为200表示请求成功
     */
    @PutMapping("/admin/changeRole/{userId}/{roleId}")
    public Result<?> changeAccountRole(
            @PathVariable("userId")
            @NotBlank(message = "用户Id未填写") String userId,
            @PathVariable("roleId")
            @NotNull(message = "角色Id未填写") Long roleId) {
        return accountService.changeAccountRole(userId, roleId);
    }
}
