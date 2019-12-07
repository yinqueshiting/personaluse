package com.example.code.config;



import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * @description: redisKey过期事件
 * @create: 2019-12-07 14:10
 **/
@Slf4j
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {


    public RedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        log.info("message.toString():{}",expiredKey);
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        log.info("message.getChannel(), StandardCharsets.UTF_8:{}",channel);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("message.getBody():{}",key);

    }

}
