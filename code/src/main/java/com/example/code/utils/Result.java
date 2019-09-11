package com.example.code.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 作为方法的返回
 */
@Setter
@Getter
@ToString
public class Result<T> implements Serializable {
    private String returnCode;
    private String returnMsg;
    private  T date;
    public static Result success() {
      return new Result(CodeMsg.SUSSESS,"成功");
    }
    public static Result success(Object date) {
        return new Result(CodeMsg.SUSSESS,"成功",date);
    }
    public static Result error() {
        return new Result(CodeMsg.EXCEPTION,"失败");
    }
    public static Result error(String returnCode,String returnMsg) {
        return new Result(returnCode,returnMsg);
    }

    public Result(String returnCode,String returnMsg){
        this.returnCode=returnCode;
        this.returnMsg=returnMsg;
        this.date = null;
    }
    public Result(String returnCode,String returnMsg,T date){
        this.returnCode=returnCode;
        this.returnMsg=returnMsg;
        this.date = date;
    }
    public Result(){

    }

}

