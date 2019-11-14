package com.example.code.queue.topic;

import com.example.code.config.rabbitmq.TopicConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description: topic交换机消息的消费者
 * @create: 2019-11-14 14:38
 **/
@Component
@Slf4j
public class TopicConsumer {

    @RabbitListener(queues = TopicConfig.TOPIC_QUEUE_ONE)
    public void consumerOne(String stringJosn){
        log.info("#接收：{}", stringJosn);
    }
    @RabbitListener(queues = TopicConfig.TOPIC_QUEUE_TWO)
    public void consumerTwo(String stringJosn, Channel channel, Message message) throws IOException {
        //在没有ack确认机制的情况下 查询此消息是否丢失
        try {
            StringBuilder stringBuilder = null;
            stringBuilder.append("2");
            log.info("one.#接收：{}", stringJosn);
        }catch (Exception e) {
            log.info("consumerTwo消息处理失败");
            //这行代码会一直重复发送
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            // 达到重试次数后用这行代码返回ack，并将消息存缓存
             channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }

    }
    @RabbitListener(queues = TopicConfig.TOPIC_QUEUE_THREE)
    public void consumerThree(String stringJosn){
        log.info("one.*接收：{}", stringJosn);
    }
}
