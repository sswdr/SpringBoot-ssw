package com.ssw.controller;

import com.ssw.model.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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
        return "student001";//student/templates/aa/student.ftl则这里写成return "aa/student";
    }

    // 引入freemarker.template包下的Configuration类，用来获取指定的模板对象
    @Resource
    private Configuration configuration;

    /**
     * 通过模板文件，生成html文件
     */
    @ApiOperation(value = "test002")
    @GetMapping("/test002")
    public String test002() {
        try {
            // 使用Configuration的getTemplate方法，指定模板文件，获取模板对象
            Template template = configuration.getTemplate("student002.ftl");
            // 绑定数据
            // 数据1：
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", "SuperCoolMan");
            map.put("age", 99);
            // 数据2：
            map.put("student", new Student("超级猛男", 18));
            FileWriter fileWriter = new FileWriter("HelloFreemarker.html");
            template.process(map, fileWriter);
            return "SUCCESS！";
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return "ERROR！";
    }
}
