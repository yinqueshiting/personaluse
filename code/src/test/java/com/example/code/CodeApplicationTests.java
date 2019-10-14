package com.example.code;

import com.example.code.config.TexProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeApplicationTests {
    @Autowired
    private TexProperties texProperties;
    @Test
    public void contextLoads() throws Exception {


    }

}
