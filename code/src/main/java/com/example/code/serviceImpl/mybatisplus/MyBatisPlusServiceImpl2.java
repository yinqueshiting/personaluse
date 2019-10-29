package com.example.code.serviceImpl.mybatisplus;

import com.example.code.entity.HhUser;
import com.example.code.service.mybatisplus.MyBatisPlusService;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 同时实现同一个接口如何注入
 * @create: 2019-10-26 17:22
 **/
@Service("s2")
@Slf4j
public class MyBatisPlusServiceImpl2 implements MyBatisPlusService {
    @Override
    public Result selectSingleUserInfo(HhUser user) throws Exception {
        return null;
    }

    @Override
    public Result updateSingleUser(HhUser user) throws Exception {
        return null;
    }

    @Override
    public Result deleteSingleUser(HhUser user) throws Exception {
        return null;
    }

    @Override
    public Result selectUserList(HhUser user) throws Exception {
        log.info("第二个实现类：{}", "只输出一句话!");
        return null;
    }
}
