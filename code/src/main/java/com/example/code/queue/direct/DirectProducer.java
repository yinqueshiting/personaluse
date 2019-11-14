package com.example.code.queue.direct;

import com.example.code.config.rabbitmq.DirectConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @description: 发送消息给direct交换机
 * @create: 2019-11-14 11:09
 **/
@Component
@Slf4j
public class DirectProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String routingKey ,String messger){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //log.info("遇到算数错误", 1/0);
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, routingKey, messger,correlationId);
    }
}
