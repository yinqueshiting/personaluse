package com.example.code.queue.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DirectDelayConsumer {
    @RabbitListener(queues = "delayDirectQueue")
    public void consumer(String stringJosn){
        log.info("延时消息收到了：",stringJosn);
    }
}
