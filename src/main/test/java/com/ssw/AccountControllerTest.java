package com.ssw;

import com.ssw.controller.AccountController;
import com.ssw.mapper.AccountMapper;
import com.ssw.po.Account;
import com.ssw.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ssw
 * @date 2022/8/22 18:12
 */
@Slf4j
@SpringBootTest
@MapperScan({"com.ssw.mapper"})
public class AccountControllerTest {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountController accountController;

    @org.junit.jupiter.api.Test
    void testAdd() {
        System.out.println(accountController.add(new Account(null, "999", 1, 1)));
    }

    @org.junit.jupiter.api.Test
    void testGetAllList() {

        accountService.CKGetList().forEach(System.out::println);

        // accountController.getList().forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    void testGetById() {
        System.out.println(accountController.getById("1"));
    }

}
