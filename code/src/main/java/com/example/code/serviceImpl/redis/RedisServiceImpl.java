package com.example.code.serviceImpl.redis;

import com.example.code.service.redis.RedisService;
import com.example.code.utils.RedisUtils;
import com.example.code.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisUtils redisUtils;
    @Override
    public Result redisTest() throws Exception{
        redisUtils.set("test","哈哈");
        return Result.success();
    }
}
