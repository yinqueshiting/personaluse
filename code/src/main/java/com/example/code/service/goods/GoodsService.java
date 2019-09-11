package com.example.code.service.goods;

import com.example.code.utils.Result;

import java.util.Map;

public interface GoodsService {
    Result selectGoogsList(Map<String,String> paramMap)throws Exception;
    Result updateGoodsInfo(Map<String,String> paramMap)throws Exception;

    Result buyGoodsForUpdate(Map<String, String> paramMap)throws Exception;

    Result forUpdateNoHaveTransation(Map<String, String> paramMap)throws Exception;

    Result buyGoodsRedis(Map<String, String> paramMap)throws Exception;
}
