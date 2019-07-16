package com.example.code.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 用redis做缓存
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public boolean set(String key,Object value)throws Exception{
        redisTemplate.opsForValue().set(key,value);
        return true;
    }

    /**
     * 用redis取缓存
     * @param key
     * @return
     * @throws Exception
     */
    public Object get(String key)throws Exception{
        Object object = redisTemplate.opsForValue().get(key);
        return object;
    }
    /**
     * redis更新缓存
     */
    public boolean update(String key,Object value)throws Exception{
       redisTemplate.opsForValue().getAndSet(key, value);
        return true;
    }
    /**
     * redis删除缓存
     */
    public boolean delete(String key)throws Exception{
        redisTemplate.delete(key);
        return true;
    }
}
