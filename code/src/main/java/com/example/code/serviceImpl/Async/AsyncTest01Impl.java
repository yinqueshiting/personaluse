package com.example.code.serviceImpl.Async;

import com.example.code.service.Async.AsyncTest01;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;


@Service
@Slf4j
public class AsyncTest01Impl implements AsyncTest01 {

    @Override
    @Async("asyncPoolTaskExecutor")
    public void AsyncTest01() throws Exception {
      log.info("接口实现类开始");
      Thread.sleep(5000);
      log.info("接口实现类结束");
    }

    @Override
    @Async("asyncPoolTaskExecutor")
    public Future<Result> AsyncHaveReturn() throws Exception {
        log.info("带有返回值的异步开启");
        Thread.sleep(10000);
        log.info("带有返回值的异步结束");
        return new AsyncResult<Result>(Result.success());
    }
}
