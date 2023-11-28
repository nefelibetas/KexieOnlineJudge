package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.dto.AccountDTO;
import com.fish.entity.pojo.Account;
import com.fish.service.AccountService;
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
    @GetMapping("/admin/gets")
    public Result<ArrayList<Account>> getAccounts() {
        return accountService.getAccounts();
    }
    @PutMapping("/admin/changeRole/{userId}/{roleId}")
    public Result<?> changeAccountRole(@PathVariable @NotBlank String userId, @PathVariable @NotNull Long roleId) {
        return accountService.changeAccountRole(userId, roleId);
    }
}
