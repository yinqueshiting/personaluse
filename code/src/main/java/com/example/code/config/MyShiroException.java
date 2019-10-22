package com.example.code.config;

import com.example.code.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 自定义异常
 * @Author Sans
 * @CreateTime 2019/6/15 22:56
 */
@ControllerAdvice
public class MyShiroException {
    /**
     * 处理Shiro权限拦截异常
     * 如果返回JSON数据格式请加上 @ResponseBody注解
     * @Author Sans
     * @CreateTime 2019/6/15 13:35
     * @Return Map<Object> 返回结果集
     */
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Result defaultErrorHandler(){
      return Result.NotHavePer();
    }
}