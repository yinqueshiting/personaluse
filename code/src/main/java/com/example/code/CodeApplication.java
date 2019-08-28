package com.example.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@MapperScan("com.example.code.mapper")
@ServletComponentScan("com.example.code.filter")
@EnableCaching
@EnableAsync //开启异步调用
public class CodeApplication {

    public static void main(String[] args) {

        SpringApplication.run(CodeApplication.class, args);
    }

}
