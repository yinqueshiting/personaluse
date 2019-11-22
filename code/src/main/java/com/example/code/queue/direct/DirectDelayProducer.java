package com.example.code.queue.direct;
/**
 * 延时消息
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DirectDelayProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void delaySend(String stringJson,long delayTimes){
        rabbitTemplate.convertAndSend("delayDirectExchange","delayDirectKey",stringJson,message -> {
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
        });
        log.info("发送了一个延时消息");
    }


}
