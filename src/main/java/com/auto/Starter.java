package com.auto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.auto.repository")
@EnableTransactionManagement
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class,args);
    }
}

//TODO:进度-->第67节
//TODO:用户模块-->个人中心-->前端&后端都没有;61还有通用处理可以改
//TODO:除了index.html和projectList.html之外的页面退出功能(logout)有问题(前端怎么把退出做成公用的部分以便其他页面使用?)