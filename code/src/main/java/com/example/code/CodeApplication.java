package com.example.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.code.*"})
@MapperScan(basePackages = {"com.example.code.mapper"})
@ServletComponentScan("com.example.code.filter")
@EnableCaching //开启缓存注解
@EnableAsync //开启异步调用
@EnableWebSocket//开启web socket
public class CodeApplication {

    public static void main(String[] args) {

        SpringApplication.run(CodeApplication.class, args);
    }

}
