package com.example.code.queue.fanout;

import com.example.code.config.rabbitmq.FanoutConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        //fanout不需要routingKey去与queue绑定，但是在生产者处需要（任意）填写一个key
        rabbitTemplate.convertAndSend(FanoutConfig.FANOUT_EXCHANGE, "every key", msg);
    }

}

