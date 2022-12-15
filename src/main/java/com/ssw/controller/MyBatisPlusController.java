package com.ssw.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ssw.mapper.CatMapper;
import com.ssw.po.Cat;
import com.ssw.service.CatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ssw
 * @date 2022/12/15 16:25
 */
@Api(tags = "MyBatisPlus-Test")
@Slf4j
@RestController("/mybatis")
public class MyBatisPlusController {
    @Autowired
    CatsService catsService;

    @Resource
    CatMapper catMapper;

    @ApiOperation(value = "hello()方法")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Object hello() {
        log.info("/hello请求进来了....");
        Cat getById = catsService.getById("1");
        Cat selectById = catMapper.selectById("1");
        log.info("getById: {}", getById);
        log.info("selectById: {}", selectById);
        // -----------------------------------------------------

        // 1.先查待分页全部数据,再逻辑分页(总量少)：适用于待分页全部数据可能不是sql查出来的、待分页全部数据sql查出来后可能需要进行处理、待分页全部数据不方便直接sql或者wrapper查出来
        // 当前页：可由前端传入
        long pageNum = 2L;
        // 每页显示条数：可由前端传入
        long pageSize = 2L;
        // 可用于分页查询的条件LambdaQueryWrapper，password为789
        LambdaQueryWrapper<Cat> catWap = new LambdaQueryWrapper<Cat>().eq(Cat::getPassword, "789");
        List<Cat> cats = catMapper.selectList(catWap);
        // 查全部后逻辑分页：查询数据列表(当前页的数据)
        List<Cat> pageCat = cats.stream().skip(pageSize * (pageNum - 1)).limit(pageSize).collect(Collectors.toList());
        // 模拟一个分页数据返回给前端
        Page<Cat> page = new Page<>();
        page.setCurrent(pageNum);  // 当前页
        page.setSize(pageSize);  // 每页显示条数
        page.setRecords(pageCat);  // 查询数据列表(当前页的数据)
        page.setTotal(cats.size());  // 总数
        log.info("page: {}", page);
        // {"records":[{"id":1,"name":"tom","account":"123","password":"666"},{"id":2,"name":"luci","account":"789","password":"666"}],
        // "total":14,"size":2,"current":1,"orders":[],"optimizeCountSql":true,"searchCount":true,"countId":null,"maxLimit":null,"pages":7}
        // -----------------------------------------------------

        // 2.分页查询,要那些数据查哪些（总量大）：适用于待分页全部数据可以直接sql或者wrapper查出来
        // 简单分页模型Page，current：1，size：2，searchCount：false（是否查总数）
        Page<Cat> catPage = new Page<>(1, 2, false);
        Page<Cat> pagePlus = catMapper.selectPage(catPage, catWap);
        log.info("pagePlus: {}", pagePlus);
        //{"records":[{"id":3,"name":"d","account":"789","password":"789"},{"id":4,"name":"asd","account":"789","password":"789"}],
        // "total":0,"size":2,"current":1,"orders":[],"optimizeCountSql":true,"searchCount":false,"countId":null,"maxLimit":null,"pages":0}
        // -----------------------------------------------------


        return pagePlus;
        // return "Hello, Spring Boot 2!";
    }

    @ApiOperation(value = "date()方法")
    @RequestMapping(value = "/date", method = RequestMethod.GET)
    @ApiImplicitParam(name = "time", value = "time", defaultValue = "2019-02-27 00:01:02", required = false)
    public void getDate(@RequestParam("time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = format.format(time);
        System.out.println(timeString);  //2019-02-27 00:01:02
        long timeStamp = time.getTime();
        System.out.println(timeStamp);  //1551196862000
        long l = timeStamp / 1000L;
        System.out.println(l);  //1551196862
    }

//    @Autowired
//    private RestTemplate restTemplate;

    @ApiOperation(value = "request()方法")
    @RequestMapping(value = "/request", method = RequestMethod.GET)
    @ApiImplicitParam(name = "time", value = "time", defaultValue = "2019-02-27 00:01:02", required = false)
    public void request(@RequestParam("time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date time) {


    }
}