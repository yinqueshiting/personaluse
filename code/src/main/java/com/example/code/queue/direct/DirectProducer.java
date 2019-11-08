package com.example.code.queue.direct;

import com.example.code.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 将消息发送给direct交换机的生产者
 * @create: 2019-11-08 16:51
 **/
@Component
@Slf4j
public class DirectProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * @Description 发送消息
     * @Param
     * @return
     * @Date 2019/11/8 16:53
     * @Created by xg
     */
    public void send(String routingKey,Object object){
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, routingKey,object);
        log.info("DirectProducer发送消息成功");
    }
}
