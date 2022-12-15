package com.ssw.controller;

import com.ssw.po.Account;
import com.ssw.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ssw
 * @date 2022/8/22 15:22
 */
@Api(tags = "ClickHouse-CRUD")
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @ApiOperation(value = "getById()方法")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "account-id", defaultValue = "1", required = false)
    public Account getById(@PathVariable("id") String id) {
        log.info("请求方法{}进来了....", Thread.currentThread().getStackTrace()[1].getMethodName());
        return accountService.CKGetById(id);
    }

    @ApiOperation(value = "getList()方法")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Account> getList() {
        log.info("请求方法{}进来了....", Thread.currentThread().getStackTrace()[1].getMethodName());
        return accountService.CKGetList();
    }

    @ApiOperation(value = "get()方法")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public List<Account> getAccount(@RequestBody Account account) {
        log.info("请求方法{}进来了....", Thread.currentThread().getStackTrace()[1].getMethodName());
        return accountService.CKGetAccount(account);
    }

    @ApiOperation(value = "add()方法")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean add(@RequestBody Account account) {
        log.info("请求方法{}进来了....", Thread.currentThread().getStackTrace()[1].getMethodName());
        return accountService.save(account);
    }

    @ApiOperation(value = "delete()方法")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void delete(@RequestBody Account account) {
        log.info("请求方法{}进来了....", Thread.currentThread().getStackTrace()[1].getMethodName());
        accountService.CKDeleteAccount(account);
    }

    @ApiOperation(value = "update()方法")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void update(@RequestBody Account account) {
        log.info("请求方法{}进来了....", Thread.currentThread().getStackTrace()[1].getMethodName());
        accountService.CKUpdateAccount(account);
    }


}
