package com.example.code.utils;

import lombok.Data;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @description: stream配合Lambda操作list
 * @create: 2019-10-30 13:52
 **/
@Data
public class StreamOp {
    private String name;
    private int age;
    private String sex;
    private String status;
    private StaffStream staffStream;

    public StreamOp(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    private static List<StreamOp> list = null;

    static {
        StreamOp wu = new StreamOp("wu qi", 18, "男");
        StreamOp zhang = new StreamOp("zhang san", 19, "男");
        StreamOp zhang1 = new StreamOp("zhang san", 119, "男1");
        StreamOp wang = new StreamOp("wang si", 20, "女");
        StreamOp zhao = new StreamOp("zhao wu", 20, "男");
        StreamOp chen = new StreamOp("chen liu", 21, "男");
        list = Arrays.asList(wu, zhang,zhang1, wang, zhao, chen);
    }

    public static List<StreamOp> getData() {
        return list;
    }

    public static void main(String[] args) {
        //过滤掉集合中小于等于18的男性
       /* List<StreamOp> list1 = list.stream()
                .filter(streamOp -> "男".equals(streamOp.getSex())&&streamOp.getAge()>18)
                .collect(Collectors.toList());
        System.out.println(list1);
        //System.out.println(list);
        List list2 = list.stream().filter(streamOp -> {
            if("男".equals(streamOp.getSex())&&streamOp.getAge()>18){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        System.out.println(list2);
        */
       //去重复
       /* List<StreamOp> list4 = list.stream().collect(
                collectingAndThen(toCollection(() -> new TreeSet<StreamOp>(Comparator.comparing(streamOp -> streamOp.getName()))),
                        ArrayList::new));
        System.out.println(list4);
        System.out.println(list);*/
       //取出集合中的某个key
        /*List<String> list3 = list.stream()
                .map(streamOp -> streamOp.getName())//将原list的某一项取出组成新的list
                .collect(toList());
        System.out.println(list3);*/
        StreamOp chen = new StreamOp("chen liu", 21, "男");
        //chen.setStatus("");
        Optional<StreamOp> opOptional = Optional.ofNullable(chen);
        String status = opOptional.map(streamOp -> streamOp.getStatus()).orElse("0");
        System.out.println("status:"+status);
       /* StaffStream staffStream = new StaffStream("");
        StreamOp zhao = new StreamOp("zhao wu", 20, "男");
        zhao.setStaffStream(staffStream);
        Optional<StreamOp> opOptional = Optional.ofNullable(zhao);
        String ss = opOptional.flatMap(StaffStream::getAdd())
                .

                .orElse("55");*/

    }





}
