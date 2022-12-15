package com.ssw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssw.mapper.AccountMapper;
import com.ssw.po.Account;
import com.ssw.service.AccountService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ssw
 * @date 2022/8/22 17:48
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    /**
     * CK逻辑查询：全部Account
     */
    @Override
    public List<Account> CKGetList() {
        return accountMapper.CKGetList();
    }

    /**
     * CK逻辑查询：根据id单个Account
     */
    @Override
    public Account CKGetById(String userId) {
        return accountMapper.CKGetById(userId);
    }

    /**
     * CK逻辑查询：条件查询Account
     */
    @Override
    public List<Account> CKGetAccount(Account account) {
        return accountMapper.CKGetAccount(account);
    }

    /**
     * CK逻辑删除：根据id删除Account
     */
    @Override
    public void CKDeleteAccount(Account account) {
        accountMapper.CKDeleteAccount(account);
    }

    /**
     * CK逻辑更新：根据id更新Account
     */
    @Override
    public void CKUpdateAccount(Account newAccount) {
        Account oldAccount = CKGetById(newAccount.getUserId());
        oldAccount.setCkSign(-1);
        newAccount.setCkSign(1);
        newAccount.setCkVersion(oldAccount.getCkVersion() + 1);
        this.saveBatch(Lists.newArrayList(oldAccount,newAccount));
    }


}
