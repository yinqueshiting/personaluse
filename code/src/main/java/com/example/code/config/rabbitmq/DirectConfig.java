package com.example.code.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: direct交换机例子 为测试方便 不持久化并自动删除
 * @create: 2019-11-14 10:47
 **/
@Configuration
public class DirectConfig {

    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String DIRECT_QUEUE_ONE = "directQueue11";
    public static final String DIRECT_QUEUE_TWO = "directQueue22";
    public static final String DIRECT_ROUTINGKEY_ONE = "directRouting1";
    public static final String DIRECT_ROUTINGKEY_TWO = "directRouting2";

    //声明direct类型的交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE, false, false);
    }
    //声明队列
    @Bean
    public Queue directQueueOne(){
        return new Queue(DIRECT_QUEUE_ONE, false);
    }
    @Bean
    public Queue directQueueTwo(){
        return new Queue(DIRECT_QUEUE_TWO, false);
    }

    //开始绑定
    @Bean
    public Binding directBInddOne(){
        return BindingBuilder.bind(directQueueOne()).to(directExchange()).with(DIRECT_ROUTINGKEY_ONE);
        //return BindingBuilder.bind(directQueueOne()).to(directExchange()).with()
    }
    @Bean
    public Binding directBInddOne1(){
        return BindingBuilder.bind(directQueueOne()).to(directExchange()).with(DIRECT_ROUTINGKEY_TWO);
    }
    @Bean
    public Binding directBInddTwo(){
        return BindingBuilder.bind(directQueueTwo()).to(directExchange()).with(DIRECT_ROUTINGKEY_TWO);
    }

    /**
     * 声明一个处理延时的交换机
     */
    @Bean
    public DirectExchange deadLetterExchange() {
        // 一共有三种构造方法，可以只传exchange的名字， 第二种，可以传exchange名字，是否支持持久化，是否可以自动删除，
        //第三种在第二种参数上可以增加Map，Map中可以存放自定义exchange中的参数
        return new DirectExchange("delayDirectExchange", true, false);
    }


    // 创建一个延时队列
    @Bean
    public Queue delayQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", "delayDirectExchange");
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", "delayDirectKey");
        return new Queue("delayDirectQueue", true, false, false, params);
    }
    @Bean
    //把立即消费的队列和立即消费的exchange绑定在一起
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(deadLetterExchange()).with("delayDirectKey");
    }
}
