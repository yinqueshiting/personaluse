package com.example.code.mqcallback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Description:通过实现ReturnCallback接口
 * 如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
 * rabbitmq有return机制，在rabbitTemplate中有returnCallback。找不到queue的消息都会进入到这个callback，在这个callback里把消息存到缓存，用另外线程重发。
 * @author zhuzhe
 * @date 2018/5/25 15:54
 * @email 1529949535@qq.com
 */
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("回馈消息："+message);
    }
}
