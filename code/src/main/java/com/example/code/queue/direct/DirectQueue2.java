package com.example.code.queue.direct;

import com.example.code.config.RabbitConfig;
import com.example.code.entity.HhUser;
import com.example.code.service.mybatisplus.MyBatisPlusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 与direct交换机绑定的第二个队列
 * @create: 2019-11-08 16:41
 **/
@Component
@RabbitListener(queues = RabbitConfig.DIRECT_QUEUE_TWO)
@Slf4j
public class DirectQueue2 {

    @Autowired
    @Qualifier(value = "s1")
    private MyBatisPlusService myBatisPlusService;

    @RabbitHandler
    //@RabbitListener(queues = RabbitConfig.DIRECT_QUEUE_TWO, containerFactory = "rabbitListenerContainerFactory")
    @Transactional(propagation = Propagation.REQUIRED)
    public void consumer(String string){
        try{
            myBatisPlusService.updateSingleUser(new HhUser());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
