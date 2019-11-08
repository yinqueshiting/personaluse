package com.example.code.controller.rabbitmq;

import com.example.code.config.RabbitConfig;
import com.example.code.queue.direct.DirectProducer;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: rabbitmq控制层
 * @create: 2019-11-08 10:58
 **/
@RestController
@Slf4j
public class RabbitMqController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private DirectProducer directProducer;
    /**
     * @Description 测试fanout交换机
     * @Param
     * @return
     * @Date 2019/11/8 11:02
     * @Created by xg
     */
    @PostMapping("/fanoutExchangeTest")
    public Result fanoutExchangeTest(){
        log.info("do Something:{}", "开始生产一个消息");
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "发送一个fanout交换机消息");
        return Result.success();
    }
   /**
    * @Description direct类型交换机
    * @Param 
    * @return 
    * @Date 2019/11/8 16:24
    * @Created by xg
    */
   @PostMapping("/directExchangeTest")
   public Result directExchangeTest(){
       log.info(" directExchangeTest do Something:{}", "开始生产一个消息");
       directProducer.send(RabbitConfig.DIRECT_ROUTINGKEY1, "directExchange开始发送消息");
       return Result.success();
   }
}
