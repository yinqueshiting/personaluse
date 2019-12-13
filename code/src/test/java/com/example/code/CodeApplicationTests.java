package com.example.code;

import com.example.code.config.TexProperties;
import com.example.code.entity.HhUser;
import com.example.code.entity.SysUser;
import com.example.code.mapper.mybatisplus.MyBatisPlusMapper;
import com.example.code.service.mybatisplus.MyBatisPlusService;
import com.example.code.service.shiro.SysUserService;
import com.example.code.serviceImpl.mybatisplus.MyBatisPlusServiceImpl;
import com.example.code.serviceImpl.mybatisplus.MyBatisPlusServiceImpl2;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

import static org.apache.shiro.SecurityUtils.getSubject;
//引入spring对junit4的支持
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class CodeApplicationTests {


    @Test
    public void test(){
        System.out.println("hello world");
    }
}
