package com.example.code.mapper.goods;

import com.example.code.entity.GoodsOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsMapper {
    List selectGoogsList(@Param("page") int page, @Param("rows") int rows);

    void updateGoodsInfo(@Param("goods_id")String goods_id, @Param("goods_name") String goods_name);

    String selectInventoryByGoodsId(@Param("goods_id") String goods_id);
    String selectInventoryByGoodsIdAddForUpdate(@Param("goods_id") String goods_id);

    void addOrderForThisUser(GoodsOrder goodsOrder);

    void reduceInventoryByGoodsId(@Param("count") String count,@Param("goods_id") String goods_id);

    Map forUpdateNoHaveTransation();
}
