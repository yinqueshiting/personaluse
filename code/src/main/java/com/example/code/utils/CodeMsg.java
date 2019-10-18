package com.example.code.utils;

import lombok.Data;

public class CodeMsg {
    /**
     * 执行成功
     */
    public static String SUSSESS = "0000";
    /**
     * 执行异常
     */
    public static String EXCEPTION = "0001";
    /**
     * 参数缺失
     */
    public static String PARAM_IS_NULL = "0002";
    /**
     * 已经存在该元素
     */
    public static String ELSMENT_IS_EXISTS = "0003";
    /**
     * 已经存在该元素
     */
    public static String NO_HAVE_INVENTORY = "0004";
    /**
     * 去登陆
     */
    public static String TO_LOGIN  = "0005";

    /**
     * 没有权限
     */
    public static String NOT_HAVE_PERMISSION  = "0006";
}
