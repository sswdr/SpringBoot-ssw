package com.ssw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw.po.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ssw
 * @date 2022/8/22 17:35
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    List<Account> CKGetList();

    Account CKGetById(@Param("userId") String userId);

    List<Account> CKGetAccount(@Param("account") Account account);

    void CKDeleteAccount(@Param("account") Account account);


}
