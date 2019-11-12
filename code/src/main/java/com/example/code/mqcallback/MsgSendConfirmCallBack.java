package com.example.code.mqcallback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 消息发送到交换机确认机制
 * @author zhuzhe
 * @date 2018/5/25 15:53
 * @email 1529949535@qq.com
 */
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("MsgSendConfirmCallBack  , 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息消费成功");
        } else {
            System.out.println("消息消费失败:" + cause+"\n重新发送");
        }
    }
}
