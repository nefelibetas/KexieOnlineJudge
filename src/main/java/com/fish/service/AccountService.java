package com.fish.service;

import com.fish.common.Result;
import com.fish.entity.dto.AccountDTO;
import com.fish.entity.pojo.Account;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;
import java.util.HashMap;

public interface AccountService extends IService<Account> {
    Result<HashMap<String,Object>> login(AccountDTO accountDTO);
    Result<?> register(AccountDTO accountDTO);
    Result<?> updateAccountInformation(AccountDTO accountDTO);
    Result<?> deleteAccount(String userId);
    Result<ArrayList<Account>> getAccounts();
    Result<?> changeAccountRole(String userId, Long roleId);
}
