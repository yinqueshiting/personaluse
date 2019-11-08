package com.example.code.queue.direct;

import com.example.code.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: 与direct交换机绑定的第一个队列
 * @create: 2019-11-08 16:41
 **/
@Component
@RabbitListener(queues = RabbitConfig.DIRECT_QUEUE_ONE)
@Slf4j
public class DirectQueue1 {

    @RabbitHandler
    public void consumer(String string){
        //证明消费了
        log.info("RabbitConfig.DIRECT_QUEUE_ONE接收到的消息：{}",string);
    }
}
