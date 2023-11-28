package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.dto.AccountDTO;
import com.fish.entity.pojo.Account;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.service.AccountService;
import com.fish.utils.ResultUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;
    @PostMapping("/login")
    public Result<HashMap<String,Object>> login(@Valid @RequestBody AccountDTO accountDTO) {
        return accountService.login(accountDTO);
    }
    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody AccountDTO accountDTO) {
        return accountService.register(accountDTO);
    }
    @PutMapping("/user/update")
    public Result<?> updateAccountInformation(@Valid @RequestBody AccountDTO accountDTO) {
        return accountService.updateAccountInformation(accountDTO);
    }
    @DeleteMapping("/admin/delete/{userId}")
    public Result<?> deleteAccount(@PathVariable String userId){
        return accountService.deleteAccount(userId);
    }
    @GetMapping("/admin/gets/{operate}")
    public Result<ArrayList<Account>> getAccounts(@PathVariable String operate) {
        if ("1".equals(operate))
            return accountService.getAccounts();
        else if ("2".equals(operate))
            return accountService.getAdmins();
        else
            return ResultUtil.failure(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @PutMapping("/admin/changeRole/{userId}/{roleId}")
    public Result<?> changeAccountRole(@PathVariable @NotBlank String userId, @PathVariable @NotNull Long roleId) {
        return accountService.changeAccountRole(userId, roleId);
    }
}
