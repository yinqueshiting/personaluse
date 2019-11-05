package com.example.code.utils;

import lombok.Data;

import java.util.Optional;

/**
 * @description: StreamOp中的一个属性
 * @create: 2019-10-30 18:32
 **/
@Data
public class StaffStream {
    private String add ;

    public static Optional getAdd(){
        return getAdd();
    }
    public StaffStream(String add) {
        this.add = add;
    }
}
