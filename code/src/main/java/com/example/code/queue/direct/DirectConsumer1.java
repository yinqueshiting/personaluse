package com.example.code.queue.direct;

import com.example.code.config.rabbitmq.DirectConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

/**
 * @description: 测试绑定两个routingKey
 * @create: 2019-11-14 11:14
 **/
@Component
@Slf4j
public class DirectConsumer1 {
    //@RabbitListener(queues = {DirectConfig.DIRECT_QUEUE_ONE,DirectConfig.DIRECT_QUEUE_TWO}) 选择两个代表可以交替接收不同队列的消息
    @RabbitListener(queues = DirectConfig.DIRECT_QUEUE_ONE)
    public void consumer(String messger){
        log.info("directOne接收到的消息：{}",messger);
    }
    @RabbitListener(queues = DirectConfig.DIRECT_QUEUE_TWO)
    public void consumerTwo(String messger){
        log.info("directTwo接收到的消息：{}",messger);
    }

}
