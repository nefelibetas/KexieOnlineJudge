package com.fish.service;

import com.fish.common.Result;
import com.fish.entity.pojo.Account;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;
import java.util.HashMap;

public interface AccountService extends IService<Account> {
    Result<HashMap<String,Object>> login(Account account);
    Result<?> register(Account account);
    Result<?> updateAccountInformation(Account account, String userId);
    Result<?> deleteAccount(String userId);
    Result<ArrayList<Account>> getAccounts();
    Result<ArrayList<Account>> getAdmins();
    Result<?> changeAccountRole(String userId, Long roleId);
}
