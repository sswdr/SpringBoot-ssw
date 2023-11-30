package com.ssw.controller;

import com.ssw.dto.TableDataDTO;
import com.ssw.service.TableDataService;
import com.ssw.vo.MyPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author ssw
 * @date 2023/11/30 10:18
 */
@Api(tags = "Table模块")
@RestController
public class TableDataController {

    @Autowired
    private TableDataService tableDataService;

    /**
     * {
     * "id": "",
     * "curPage": 1,
     * "pageSize": 5,
     * "orderType": "desc",
     * "sortName": "Column 2",
     * "startTime": 2,
     * "endTime": 2
     * }
     */
    @ApiImplicitParam(name = "param", value = "请求参数", required = true)
    @ApiOperation(value = "获取表格数据[自定义表格,内存分页]")
    @PostMapping("/getTableData")
    public ResponseEntity<Object> getTableData(@RequestBody @Valid TableDataDTO param, BindingResult result) {
        if (result.hasErrors()) {
            return bindError(result);
        }

        MyPage<Map<String, Object>, TableDataDTO, Object> page = new MyPage<>();
        page.setCurrent(param.getCurPage());
        page.setSize(param.getPageSize());
        page.setSortName(param.getSortName());
        page.setOrderType(param.getOrderType());
        page.setParam(param);
        return ResponseEntity.ok(tableDataService.getTableData(page));
    }

    private ResponseEntity<Object> bindError(BindingResult result) {
        StringBuilder tmp = new StringBuilder();
        result.getFieldErrors().forEach(x -> tmp.append(x.getDefaultMessage()).append(", "));
        tmp.delete(tmp.length() - 2, tmp.length());
        // if (tmp.length() > 2 && tmp.substring(tmp.length() - 2).equals(", ")) {
        //     tmp.delete(tmp.length() - 2, tmp.length());
        // }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tmp.toString());
    }
}
