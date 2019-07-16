package com.example.code.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.ImportResource;

/**
 * 测试导入resource中的xml文件
 */
@Configuration
@ImportResource("classpath:mapper.xml")
public class MyBatisXml {
    public void test(){
        System.out.print("不知道干什么");
    }
}
