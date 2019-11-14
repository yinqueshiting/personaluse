package com.example.code.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: topic交换机测试
 * @create: 2019-11-14 14:25
 **/
@Configuration
public class TopicConfig {
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String TOPIC_QUEUE_ONE= "topicExchange1";//接收所有消息 #
    public static final String TOPIC_QUEUE_TWO = "topicExchange2"; //one.#
    public static final String TOPIC_QUEUE_THREE = "topicExchange3"; //one.*
    public static final String TOPIC_ROUTINGKEY_ONE = "#"; //
    public static final String TOPIC_ROUTINGKEY_TWO = "one.#"; //
    public static final String TOPIC_ROUTINGKEY_THREE = "one.*"; //

    //声明一个topic类型的交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE, true, false);
    }
    //声明队列
    @Bean
    public Queue topicQueueOne(){
        return new Queue(TOPIC_QUEUE_ONE, true);
    }
    @Bean
    public Queue topicQueueTwo(){
        return new Queue(TOPIC_QUEUE_TWO, true);
    }
    @Bean
    public Queue topicQueueThree(){
        return new Queue(TOPIC_QUEUE_THREE, true);
    }
    //绑定关系
    @Bean
    public Binding topicBindOne(){
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(TOPIC_ROUTINGKEY_ONE);
    }
    @Bean
    public Binding topicBindTwo(){
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(TOPIC_ROUTINGKEY_TWO);
    }
    @Bean
    public Binding topicBindThree(){
        return BindingBuilder.bind(topicQueueThree()).to(topicExchange()).with(TOPIC_ROUTINGKEY_THREE);
    }
}
