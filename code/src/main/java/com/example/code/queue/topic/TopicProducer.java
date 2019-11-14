package com.example.code.queue.topic;

import com.example.code.config.rabbitmq.TopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 向topic交换机提供消息的生产者
 * @create: 2019-11-14 14:35
 **/
@Component
@Slf4j
public class TopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String routingKey,String stringJson){
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, routingKey, stringJson);
        log.info("TopicProducer发出消息");
    }
}
