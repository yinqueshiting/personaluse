package com.example.code.controller;

import com.example.code.entity.HhUser;
import com.example.code.service.mybatisplus.MyBatisPlusService;
import com.example.code.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: mybatis-plus控制层
 * @create: 2019-10-26 10:58
 **/
@RestController
public class MyBatisPlusController {
    @Autowired
    @Qualifier(value = "s2")
    private MyBatisPlusService myBatisPlusService;

    @PostMapping("/selectSingleUserInfo")
    public Result selectSingleUserInfo(@RequestBody HhUser user){
        try{
            return myBatisPlusService.selectSingleUserInfo(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
    @PostMapping("/updateSingleUser")
    public Result updateSingleUser(@RequestBody HhUser user){
        try{
            return myBatisPlusService.updateSingleUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
    @PostMapping("/deleteSingleUser")
    public Result deleteSingleUser(@RequestBody HhUser user){
        try{
            return myBatisPlusService.deleteSingleUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    @PostMapping("/selectUserList")
    public Result selectUserList(@RequestBody HhUser user){
        try{
            return myBatisPlusService.selectUserList(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

}
