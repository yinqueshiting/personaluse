package com.example.code.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 测试fanout（扇形）交换机
 * @create: 2019-11-14 09:31
 **/
@Configuration
public class FanoutConfig {
    //定义交换机名称
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    //定义队列名称
    public static final String FANOUT_QUEUE_ONE = "fanoutQueue1";
    public static final String FANOUT_QUEUE_TWO = "fanoutQueue2";
    public static final String FANOUT_QUEUE_THREE = "fanoutQueue3";

    //声明交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE, false, false);
    }
    //声明队列
    @Bean
    public Queue fanoutQueue1() {
        //第二个参数durable代表是否持久化 ；选择false是为了测试方便
       return new Queue(FANOUT_QUEUE_ONE, false);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_TWO,false);
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue(FANOUT_QUEUE_THREE,false);
    }

    //将交换机与队列绑定 fanout类型的不需要routingKey
    @Bean
    public Binding bindFanoutQueue1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindFanoutQueue2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    @Bean
    public Binding bindFanoutQueue3() { return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());}

}
