package com.example.code;

import com.example.code.config.TexProperties;
import com.example.code.entity.SysUser;
import com.example.code.service.shiro.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.IOException;
import java.util.ResourceBundle;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CodeApplicationTests {
    @Autowired
    private TexProperties texProperties;

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void contextLoads() throws Exception {
        /*SysUser sysUser = sysUserService.selectUserInfo("admin");
        log.info("user:{}", sysUser);
        log.info("用户角色集合：{}", sysUserService.selectRoleByUserId(2));
        log.info("用户权限集合：{}", sysUserService.selelctMenuByRoleId(2));
    */
        System.out.println("??:"+new String(texProperties.getSuccess().getBytes("ISO-8859-1"),"GBK"));
        System.out.println("??:"+texProperties.getNo_hava_iinventory());
        ResourceBundle resource =  ResourceBundle.getBundle("txt");
        String title1 = new String( resource.getString("ELEMENT_IS_EXISTS").getBytes("ISO-8859-1"),"GBK");
        System.out.println(title1);
    }

}
