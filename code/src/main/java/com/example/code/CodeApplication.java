package com.example.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.code.*"})
@MapperScan(basePackages = {"com.example.code.mapper"})
@ServletComponentScan("com.example.code.filter")
@EnableCaching //开启注解
@EnableAsync //开启异步调用
public class CodeApplication {

    public static void main(String[] args) {

        SpringApplication.run(CodeApplication.class, args);
    }

}
