package com.ssw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssw
 * @date 2022/11/22 11:28
 */
@Api(tags = "Index模块")
@RestController
public class IndexController {

    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    @ApiOperation(value = "方法001")
    @GetMapping("/getName")
    public ResponseEntity<String> getName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(name);
    }
}