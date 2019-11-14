package com.example.code.queue.fanout;

import com.example.code.config.rabbitmq.FanoutConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
//@RabbitListener(queues = {FanoutConfig.FANOUT_QUEUE_ONE, FanoutConfig.FANOUT_QUEUE_TWO, FanoutConfig.FANOUT_QUEUE_THREE})
public class FanoutConsumer {

    //@RabbitHandler
    /*public void process(String msg) {
        System.out.println(msg);
    }
    */
    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_ONE)
    public void consumerOne(String msg) {
        log.info("消费者1收到了：{}", msg);
    }
    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_TWO)
    public void consumerTwo(String msg) {
        log.info("消费者2收到了：{}", msg);
    }
    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_THREE)
    public void consumerThree(String msg) {
        log.info("消费者3收到了：{}", msg);
    }
}

