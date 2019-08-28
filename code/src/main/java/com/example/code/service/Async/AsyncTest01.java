package com.example.code.service.Async;

import com.example.code.utils.Result;

import java.util.concurrent.Future;

public interface AsyncTest01 {
    void AsyncTest01()throws Exception;
    Future<Result> AsyncHaveReturn()throws Exception;

}
