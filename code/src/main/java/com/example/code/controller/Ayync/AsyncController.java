package com.example.code.controller.Ayync;

import com.example.code.service.Async.AsyncTest01;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/Async")
@Slf4j
public class AsyncController {
    @Autowired
    private AsyncTest01 asyncTest01;

    /**
     * 没有返回值的异步调用 实现类还在睡眠中 但是控制层已经返回了
     * @return
     */
    @PostMapping("/AsyncTest01")
    public Result AsyncTest01(@RequestBody Map<String,String> paramMap){
        log.info("接口开始{}",paramMap);
        try {
            asyncTest01.AsyncTest01();
            log.info("接口结束");
            Map resMap = new HashMap();
            resMap.put("keys", "values");
            return Result.success(resMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 有返回值的异步
     * 单个读取时 控制层等待实现类的睡眠结束再返回
     * @return
     */
    @PostMapping("/AsyncHaveReturn")
        public Result AsyncHaveReturn(){
            log.info("接口开始");
            try {
                Future<Result> result =  asyncTest01.AsyncHaveReturn();
                log.info("接口结束");
                //log.info("异步获取的数据：{}",result.get().toString()); 如果直接这样处理 接口放回会等待异步处理完再返回 没有达到预期的效果
                if(!result.isDone()){ //采用判断 如果异步没有结束 那么也不继续等待
                    return Result.success();
                }else{
                    log.info("异步获取的数据：{}",result.get().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result.error();
    }
    @PostMapping("/AsyncHaveReturnList")
    public Result AsyncHaveReturnList(){
        log.info("接口开始");
        try {
            List<Future> futureList = new ArrayList<Future>();
            for(int i =0;i<=9 ;i++){
                Future<Result> result =  asyncTest01.AsyncHaveReturn();
                futureList.add(result);
            }
            /*
            for(Future future:futureList){
                Result result = (Result) future.get();
                log.info("异步获取的数据：{}",result.toString());
            }*/
            log.info("接口结束");
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }


}
