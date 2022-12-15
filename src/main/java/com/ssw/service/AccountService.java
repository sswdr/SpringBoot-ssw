package com.ssw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssw.po.Account;

import java.util.List;

/**
 * @author ssw
 * @date 2022/8/22 17:49
 */
public interface AccountService extends IService<Account> {

    List<Account> CKGetList();

    Account CKGetById(String userId);

    List<Account> CKGetAccount(Account account);

    void CKDeleteAccount(Account account);

    void CKUpdateAccount(Account account);


}
