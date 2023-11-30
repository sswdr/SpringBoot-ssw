package com.ssw;

import com.ssw.config.NoDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author ssw
 * @date 2022/11/22 10:49
 */
//配置一个空的数据源，用于无数据源启动项目
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import(NoDataSourceConfig.class)
public class MainApplicationNoDataSource {
    public static void main(String[] args) {
        SpringApplication.run(MainApplicationNoDataSource.class, args);
    }
}
