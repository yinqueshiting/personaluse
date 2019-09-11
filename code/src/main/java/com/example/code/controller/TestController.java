package com.example.code.controller;

import com.example.code.service.SpringData.UserService;
import com.example.code.service.TestService;
import com.example.code.service.redis.RedisService;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    /*@PostMapping("/test")
    public Result test(@RequestBody Map paramMap){
      Map resMap = testService.test();
      return Result.success(resMap);
    }*/

    @PostMapping("/findUserById")
    public Result findUserById(@RequestBody Map<String,Integer> paramMap) {
        log.info("paramMap{}" + paramMap);
        int id = paramMap.get("id");
        log.info("id[]{}",id);
        try {
            String s= null;
            s.toString();
            return userService.findUserById(id);
        } catch (Exception e) {
           log.error("findUserById 异常信息{}",e);
        }
        return Result.error();
    }

    /**
     * 使用map作为返回
     * @return
     */
    @PostMapping("/findUserReturnMap")
    public Result findUserReturnMap(@RequestBody Map<String,String> paramMap){
        log.info("使用map作为返回参数{}",paramMap);
        String name = paramMap.get("name");
        try {
            return userService.findUserReturnMap(name);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    @PostMapping("/redisTest")
    public Result redisTest(){
        try {
             return redisService.redisTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

}
