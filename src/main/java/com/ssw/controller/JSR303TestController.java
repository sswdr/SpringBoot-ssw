package com.ssw.controller;

import com.ssw.dto.RequestDTO;
import com.ssw.service.JSR303TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author ssw
 * @date 2022/11/22 10:49
 */
@Api(tags = "JSR303TestController")
@RestController
@Slf4j
public class JSR303TestController {

    @Resource
    private JSR303TestService jsr303TestService;

    @RequestMapping(value = "/test01/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "test01", notes = "notes")
    public ResponseEntity<Integer> test01(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(id);
    }

    @RequestMapping(value = "/test02/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "test02", notes = "notes")
    public ResponseEntity<Integer> test02(@PathVariable(value = "id") @Valid Integer id, BindingResult result) {
        return ResponseEntity.ok(id);
    }

    @RequestMapping(value = "/test03/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "test03", notes = "notes")
    public ResponseEntity<RequestDTO> test03(@Valid @RequestBody RequestDTO requestDTO01, BindingResult result01,
                                             @Valid @RequestBody RequestDTO requestDTO02, BindingResult result02) {

        jsr303TestService.method01(result01);
        return ResponseEntity.ok(requestDTO01);
    }

}
