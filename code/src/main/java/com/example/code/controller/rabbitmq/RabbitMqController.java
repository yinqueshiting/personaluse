package com.example.code.controller.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.example.code.queue.direct.DirectProducer;
import com.example.code.queue.fanout.FanoutProducer;
import com.example.code.queue.topic.TopicProducer;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: rabbitmq控制层
 * @create: 2019-11-08 10:58
 **/

@RestController
@Slf4j
public class RabbitMqController {

    @Autowired
    private FanoutProducer fanoutProducer;

    @Autowired
    private DirectProducer directProducer;

    @Autowired
    private TopicProducer topicProducer;

/**
     * @Description 测试fanout交换机
     * @Param
     * @return
     * @Date 2019/11/8 11:02
     * @Created by xg
     */

    @PostMapping("/fanoutExchangeTest")
    public Result fanoutExchangeTest(){
        List<Integer> list = new ArrayList();
        list.add(58);
        list.add(125);

        String strinfJson = JSON.toJSONString(list);
        fanoutProducer.send(strinfJson);
        log.info("do Something:{}", "开始生产一个消息");
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
   public Result directExchangeTest(@RequestParam("routingKey")String routingKey,@RequestParam("messger")String messger){
      directProducer.send(routingKey, messger);
      log.info("direct交换机消息开始发送");
       return Result.success();
   }

   /**
    * @Description 测试topic类型的交换机
   * @Param
    * @return
    * @Date 2019/11/14 14:45
    * @Created by xg
    */
   @PostMapping("/topicExchangeTest/{routingKey}/{messger}")
   public Result topicExchangeTest(@PathVariable String routingKey, @PathVariable String messger){
       topicProducer.send(routingKey, messger);
       log.info("topic交换机消息开始发送");
       return Result.success();
   }
}
