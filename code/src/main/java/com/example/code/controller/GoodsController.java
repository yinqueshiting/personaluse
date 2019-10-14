package com.example.code.controller;

import com.example.code.dto.OrderInputDTO;
import com.example.code.service.goods.GoodsService;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/selectGoogsList")
    public Result selectGoogsList(@RequestBody Map<String,String> paramMap){
        log.info("查询商品列表参数:{}",paramMap);
        try{
            return goodsService.selectGoogsList(paramMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    @PostMapping("/updateGoodsInfo")
    public Result updateGoodsInfo(@RequestBody Map<String,String> paramMap){
        log.info("查询商品列表参数:{}",paramMap);
        try{
            return goodsService.updateGoodsInfo(paramMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 只通过行锁的形式来操作
     * @param paramMap
     * @return
     */
    @PostMapping("/buyGoodsForUpdate")
    public Result buyGoodsForUpdate(@RequestBody Map<String,String> paramMap){
        log.info("查询商品列表参数:{}",paramMap);
        try{
            return goodsService.buyGoodsForUpdate(paramMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 简单的通过redis的list来操作
     * @param
     * @return
     */
    @PostMapping("/buyGoodsRedis")
    public Result buyGoodsRedis(@RequestBody OrderInputDTO orderInputDTO){
        log.info("简单的通过redis的list来操作:{}",orderInputDTO);
        try{
            return goodsService.buyGoodsRedis(orderInputDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 添加行锁 不加事务
     * @param paramMap
     * @return
     */
    @PostMapping("/forUpdateNoHaveTransation")
    public Result forUpdateNoHaveTransation(@RequestBody Map<String,String> paramMap){
        log.info("查询商品列表参数:{}",paramMap);
        try{
            return goodsService.forUpdateNoHaveTransation(paramMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

}
