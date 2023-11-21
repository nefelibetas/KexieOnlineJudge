package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.pojo.Account;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.service.AccountService;
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
    @PostMapping("/login")
    public Result<HashMap<String,Object>> login(
            @Valid
            @RequestBody Account account) {
        return accountService.login(account);
    }
    @PostMapping("/register")
    public Result<?> register(
            @Valid
            @RequestBody Account account) {
        return accountService.register(account);
    }
    @PutMapping("/user/update/{userId}")
    public Result<?> updateAccountInformation(
            @Valid
            @RequestBody Account account,
            @PathVariable
            @NotBlank(message = "用户Id不能为空") String userId) {
        return accountService.updateAccountInformation(account, userId);
    }
    @DeleteMapping("/admin/delete/{userId}")
    public Result<?> deleteAccount(
            @PathVariable
            @NotBlank(message = "用户Id未填写") String userId){
        return accountService.deleteAccount(userId);
    }
    @GetMapping("/admin/gets/{operate}")
    public Result<ArrayList<Account>> getAccounts(
            @PathVariable
            @Pattern(regexp = "^([12])$", message = "只能从1和2中选择") String operate) {
        if ("1".equals(operate))
            return accountService.getAccounts();
        else if ("2".equals(operate))
            return accountService.getAdmins();
        else
            return ResultUtil.failure(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @PutMapping("/admin/changeRole/{userId}/{roleId}")
    public Result<?> changeAccountRole(
            @PathVariable
            @NotBlank(message = "用户Id未填写") String userId,
            @PathVariable
            @NotNull(message = "角色Id未填写") Long roleId) {
        return accountService.changeAccountRole(userId, roleId);
    }
}
