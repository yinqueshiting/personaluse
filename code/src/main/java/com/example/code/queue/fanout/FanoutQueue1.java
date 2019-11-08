package com.example.code.queue.fanout;

import com.example.code.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: 第一个与fanout交换机绑定的队列
 * @create: 2019-11-08 11:23
 **/
@Component
@RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_ONE)
@Slf4j
public class FanoutQueue1 {

    @RabbitHandler
    public void fanoutQueue1(String object){
        log.info("fanoutQueue1接收发送的消息：{}",object);
    }
}
