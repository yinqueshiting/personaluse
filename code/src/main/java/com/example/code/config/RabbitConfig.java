/*
package com.example.code.config;

*/
/**
 * @description: RabbitMQ配置
 * @create: 2019-11-07 09:14
 **//*


import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

*/
/**
 Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 Queue:消息的载体,每个消息都会被投到一个或多个队列。
 Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
 vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 Producer:消息生产者,就是投递消息的程序.
 Consumer:消息消费者,就是接受消息的程序.
 Channel:消息通道,在客户端的每个连接里,可建立多个channel.
 *//*

@Configuration
@Slf4j
public class RabbitConfig {


    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Autowired
    private ConnectionFactory connectionFactory;

*/
/*
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        log.info("{},{},{},{}", host,port,username,password);
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

   @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
*//*

    */
/*
        fanout交换机的名称
     *//*

    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    */
/*
        队列名称
     *//*

    public static final String FANOUT_QUEUE_ONE = "fanoutQueue1";
    public static final String FANOUT_QUEUE_TWO = "fanoutQueue2";
    public static final String FANOUT_QUEUE_THREE = "fanoutQueue3";
    */
/*
     *使用fanout类型的交换机
     * 1:首先声明一个fanout交换机（fanout类型的交换机会将接收到的消息广播给所有与之绑定的队列 忽略了routing key）
     *//*

   */
/* @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    //申明与fanout类型绑定的队列
    @Bean
    public Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE_ONE);
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue(FANOUT_QUEUE_TWO);
    }
    @Bean
    public Queue fanoutQueue3(){
        return new Queue(FANOUT_QUEUE_THREE);
    }
    //将fanout队列与fanout交换机绑定
    @Bean
    Binding fanoutBind1(Queue fanoutQueue1,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }
    @Bean
    Binding fanoutBind2(Queue fanoutQueue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
    @Bean
    Binding fanoutBind3(Queue fanoutQueue3,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }
    *//*

    */
/*
        fanout交换机结束
     *//*

    */
/*
        direct类型交换机
     *//*

    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String DIRECT_QUEUE_ONE = "directQueue1";
    public static final String DIRECT_QUEUE_TWO = "directQueue2";

    public static final String DIRECT_ROUTINGKEY1 = "directKey1";
    public static final String DIRECT_ROUTINGKEY2 = "directKey2";

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE, true, false);
    }
    @Bean
    public Queue directQueue1(){
        return new Queue(DIRECT_QUEUE_ONE, true, false, false);
    }
    @Bean
    public Queue directQueue2(){
        return new Queue(DIRECT_QUEUE_TWO, true, false, false);
    }
    //return BindingBuilder.bind(queueConfig.secondQueue()).to(exchangeConfig.directExchange()).with(RabbitMqConfig.ROUTINGKEY2);
    @Bean
    public Binding directBinding1(){
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with(DIRECT_ROUTINGKEY1);
    }
    @Bean
    public Binding directBinding2(){
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(DIRECT_ROUTINGKEY2);
    }

    */
/**
     * queue listener  观察 监听模式
     * 当有消息到达时会通知监听在对应的队列上的监听对象
     * @return
     *//*

   */
/* @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer_one(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.addQueues(directQueue1());
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        return simpleMessageListenerContainer;
    }*//*


    */
/**
     * 定义rabbit template用于数据的接收和发送
     * @return
     *//*

    //@Bean
    */
/*public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        *//*
*/
/**若使用confirm-callback或return-callback，
         * 必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback
         *//*
*/
/*
        template.setConfirmCallback(msgSendConfirmCallBack());
        //template.setReturnCallback(msgSendReturnCallback());
        *//*
*/
/**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
         * 可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥
         *//*
*/
/*
        //  template.setMandatory(true);
        return template;
    }*//*


    */
/**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务。
     * @return
     *//*

    */
/*@Bean
    public MsgSendConfirmCallBack msgSendConfirmCallBack(){
        return new MsgSendConfirmCallBack();
    }*//*




}
*/
