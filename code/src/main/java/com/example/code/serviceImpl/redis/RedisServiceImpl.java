package com.example.code.serviceImpl.redis;

import com.example.code.service.redis.RedisService;
import com.example.code.utils.RedisUtils;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private RedisTemplate<String, List> redisTemplate;
    @Override
    public Result redisTest() throws Exception{
       ListOperations listOperations = redisTemplate.opsForList();
       listOperations.leftPush("list", "02");
        listOperations.rightPush("list", "A");
        listOperations.rightPush("list", "B");
        List list = listOperations.range("list", 0, listOperations.size("list"));
        redisTemplate.expire("list", 1, TimeUnit.MINUTES);
        log.info("缓存的有效时间：{}",redisTemplate.getExpire("list"));listOperations.leftPush("list", "01");
        log.info("list的长度：{}",listOperations.size("list"));

       return Result.success(list);
    }
}
