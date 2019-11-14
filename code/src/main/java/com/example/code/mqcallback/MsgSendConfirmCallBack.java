package com.example.code.mqcallback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 通过实现ConfirmCallBack接口，消息发送到交换器Exchange后触发回调
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
            System.out.println("消息成功达到交换机");
        } else {
            System.out.println("消息到大交换机失败:" + cause+"\n重新发送");
        }
    }
}
