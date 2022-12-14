package com.ssw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author ssw
 * @date 2022/12/14 11:24
 */
@Api(tags = "freemarker测试模块")
@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {

    @ApiImplicitParam(name = "map", value = "形参map", required = false)
    @ApiOperation(value = "test001")
    @PostMapping("/test001")
    public String test001(Map<String, Object> map) {//形参map，放在springmvc的形参最终request会响应给用户，也就是这个map虽然只是放在形参，但是request域里面的数据会拿到这些map信息，也就是freeMark在生成模板的时候就可以拿到map里的数据。
        //map就是freeMark模板所使用的数据
        map.put("name", "freemarker");
        //模板文件名称：返回freemark模板位置，基于resource/templates路径。
        return "student";//student/templates/aa/student.ftl则这里写成return "aa/student";
    }
}
