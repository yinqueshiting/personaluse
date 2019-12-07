package com.example.code.controller;

import com.example.code.config.ShiroRealm;
import com.example.code.config.SpringUtil;
import com.example.code.entity.SysUser;
import com.example.code.entity.HhUser;
import com.example.code.service.SpringData.UserService;
import com.example.code.service.TestService;
import com.example.code.service.redis.RedisService;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);
    /*@PostMapping("/test")
    public Result test(@RequestBody Map paramMap){
      Map resMap = testService.test();
      return Result.success(resMap);
    }*/
    @PostMapping("/test")
    public Result test(@RequestBody Map paramMap){
        redisTemplate.opsForValue().set("key:expireKey:key1", "yoyyoyoyowww");
        redisTemplate.expire("key:expireKey:key1", 20, TimeUnit.SECONDS);
       return  Result.success();
    }
   /* @PostMapping("/findUserById")
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
    }*/

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

    @PostMapping("/seelctUserDetails")
    @RequiresRoles(value={"ADMIN"})
    public Result seelctUserDetails(@RequestBody HhUser userMap){
        try {
            log.info("seelctUserDetails参数：{}",userMap);
            return userService.seelctUserDetails(userMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }
    @PostMapping("/updateUserDetails")
    public Result updateUserDetails(@RequestBody HhUser user){
        try {
            log.info("updateUserDetails：{}",user);
            return userService.updateUserDetails(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    @PostMapping("/addUserInfo")
    @RequiresPermissions(value = "sys:role:info")
    public Result addUserInfo(@RequestBody HhUser HhUser){
        try {
            log.info("addUserInfo：{}",HhUser);
            //return userService.addUserInfo(HhUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }
    /**
     * @Description 对user的权限进行修改，然后看缓存中的权限是否会刷新
     * @Param
     * @return
     * @Date 2019/10/23 15:04
     * @Created by xg
     */
    @PostMapping("/updateUserMenu")
    public Result updateUserMenu(@RequestBody HhUser HhUser){
        try {
            //先修改用户的名称是否会有效果 因为是根据名称来删除
            userService.updateUserName("userU",2);
            int role_id = 2;
            int menu_id = 2;
            Result result = userService.addUserMenu(role_id,menu_id);
            log.info("删除user的cache");
            //从缓存中获取Session
            Session session = null;
            Collection<Session> sessions = redisSessionDAO.getActiveSessions();
            SysUser sysUserEntity;
            Object attribute = null;
            for(Session sessionInfo : sessions){
                //遍历Session,找到该用户名称对应的Session
                attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                log.info("每次遍历的attribute为{}", attribute);
                if (attribute == null) {
                    continue;
                }
                sysUserEntity = (SysUser) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
                if (sysUserEntity == null) {
                    continue;
                }
                log.info("每次遍历的SysUser为{}", sysUserEntity);


                log.info("shiro获取session:{}", SecurityUtils.getSubject().getSession());
                if (Objects.equals(sysUserEntity.getUserId(), 2)) {// 2代表userId 先写固定的的值
                    session=sessionInfo;
                    break;
                }
            }
            if (session == null||attribute == null) {
                log.info("session为空");
                return Result.error();
            }
            log.info("session:{}",session);
            //删除Cache，在访问受限接口时会重新授权
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            Authenticator authc = securityManager.getAuthenticator();
            ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }
    /**
     * @Description 尝试使用securityUtils
     * @Param
     * @return
     * @Date 2019/10/24 14:08
     * @Created by xg
     */
    @PostMapping("/updateUserMenuBySecurity")
    public Result updateUserMenuBySecurity(@RequestBody HhUser HhUser){
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm myShiroRealm = (ShiroRealm) rsm.getRealms().iterator().next();

        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        log.info("realmName:{}",realmName);
        SimplePrincipalCollection principals = new SimplePrincipalCollection(SecurityUtils.getSubject().getPrincipal(), realmName);
        log.info("SecurityUtils.getSubject().getPrincipal():{}", SecurityUtils.getSubject().getPrincipal());
        subject.runAs(principals);
        if(myShiroRealm.isAuthenticationCachingEnabled()) {
            myShiroRealm.getAuthenticationCache().remove(principals);
        }
        if(myShiroRealm.isAuthorizationCachingEnabled()) {
            // 删除指定用户shiro权限
            myShiroRealm.getAuthorizationCache().remove(principals);
        }
        // 刷新权限
        subject.releaseRunAs();
        int role_id = 1;
        int menu_id = 4;
        try{
            Result result = userService.addUserMenu(role_id,menu_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.success();
    }

    /**
     * @Description 文件上传
     * @Param
     * @return
     * @Date 2019/10/31 9:44
     * @Created by xg
     */
    @PostMapping("/fileUpload")
    public Result fileUpload(@RequestParam("files") MultipartFile[] files , HttpServletRequest request){
        log.info("Multipart files:{}",files);
        //获取项目在容器中的路径
        String fileRootPath = request.getServletContext().getRealPath("/");
        String filePath = null;
        for (MultipartFile file : files){
            // 上传简单文件名
            String originalFilename = file.getOriginalFilename();
            // 存储路径
            filePath = new StringBuilder(fileRootPath)
                    .append(System.currentTimeMillis())
                    .append(originalFilename)
                    .toString();
            try {
                // 保存文件
                file.transferTo(new File(filePath));
                //将文件目录\\替换为\
                filePath.replace("\\\\", "\\");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.success(filePath);
    }
}
