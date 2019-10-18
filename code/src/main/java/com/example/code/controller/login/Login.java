package com.example.code.controller.login;

import com.example.code.entity.SysUser;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 登陆控制层
 * @create: 2019-10-17 17:03
 **/
@RestController
@Slf4j
@RequestMapping("/static")
public class Login {

    @RequestMapping("/toLogin")
    public Result index(){
        Map<String,String> resMap = new HashMap();
        resMap.put("index", "index");
        log.info("调用了index接口");
        return Result.success(resMap);
    }

    @RequestMapping("/success")
    public Result success(){
        Map<String,String> resMap = new HashMap();
        resMap.put("success", "success");
        log.info("调用了success接口");
        return Result.success(resMap);
    }

    @RequestMapping("/error")
    public Result error(){
        Map<String,String> resMap = new HashMap();
        resMap.put("error", "error");
        log.info("调用了error接口");
        return Result.success(resMap);
    }

    @RequestMapping("/userlogin")
    public Result userlogin(@RequestBody SysUser sysUser){
        log.info("调用了userlogin接口");
        Map<String,String> map = new HashMap();
        try{
            //验证身份和登陆
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
            //验证成功进行登录操作
            subject.login(token);
        }catch (IncorrectCredentialsException e) {
            map.put("code","501");
            map.put("msg","密码错误");
            return Result.success(map);
        } catch (LockedAccountException e) {
            map.put("code","502");
            map.put("msg","登录失败，该用户已被冻结");
            return Result.success(map);
        } catch (AuthenticationException e) {
            map.put("code","503");
            map.put("msg","该用户不存在");
            return Result.success(map);
        } catch (Exception e) {
            map.put("code","504");
            map.put("msg","未知异常");
            return Result.success(map);
        }
        map.put("code","000");
        map.put("msg","登录成功");
        //map.put("token",ShiroUtils.getSession().getId().toString());
        return Result.success(map);
    }
}
