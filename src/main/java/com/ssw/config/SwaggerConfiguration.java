package com.ssw.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author ssw
 * @date 2022/11/22 11:00
 */
@Configuration
@EnableSwagger2WebMvc // 该注解是Springfox-swagger框架提供的使用Swagger注解，该注解必须加
//@EnableKnife4j // 该注解是knife4j提供的增强注解,Ui提供了例如动态参数、参数过滤、接口排序等增强功能,如果你想使用这些增强功能就必须加该注解，否则可以不用加
//@Import(BeanValidatorPluginsConfiguration.class) // 如果要使用JSR303注解，需要在创建Swagger的配置文件中导入springfox的关于JSR303的配置文件
public class SwaggerConfiguration {

    // 引入Knife4j提供的扩展类
    // OpenApiExtensionResolver辅助类需要配置knife4j.enable=true才能自动@Autowired
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        // 分组名称
        String groupName = "Docket groupName";
        // 指定使用Swagger2规范
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host("Docket host")
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.ssw.controller"))
                .paths(PathSelectors.any())
                .build()
                // 赋予插件体系；buildExtensions方法需要传入分组名称,该分组名称主要是为了区分开发者在构建自定义文档时，在不同的Docket逻辑分组下进行区别显示
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ApiInfo title")
                // 描述字段支持Markdown语法
                .description("ApiInfo description")
                .version("ApiInfo version")
                .contact(new Contact("ssw", "ssw-url", "ssw-email"))
                .build();
    }
}
