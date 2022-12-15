package com.ssw.controller;

import com.ssw.mapper.AccountMapper;
import com.ssw.mapper.CatMapper;
import com.ssw.po.Account;
import com.ssw.po.Cat;
import com.ssw.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

/**
 * @author ssw
 * @date 2022/9/5 20:11
 */
@Api(tags = "DataSource-Test")
@Slf4j
@RestController
@RequestMapping("/datasource")
public class DataSourceTestController {
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    CatMapper catMapper;

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "multipleDataSources()方法")
    @RequestMapping(value = "/multipleDataSources", method = RequestMethod.GET)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object multipleDataSources() throws Exception {
        LinkedList<Object> result = new LinkedList<>();
        Account account = accountMapper.CKGetById("1");
        result.add(account);
        Cat cat = catMapper.selectById("1");
        result.add(cat);
        return result;
    }

    @ApiOperation(value = "dataSourcesException()方法")
    @RequestMapping(value = "/dataSourcesException", method = RequestMethod.GET)
    @Transactional
    public void dataSourcesException() throws Exception {
        Cat cat = new Cat();
        cat.setName("cat");
        catMapper.insert(cat);
        if (1 == 1) {
            throw new Exception();
        }
        Account account = new Account();
        account.setUserData("acc");
        account.setCkSign(1);
        account.setCkVersion(1);
        accountMapper.insert(account);
    }
}
