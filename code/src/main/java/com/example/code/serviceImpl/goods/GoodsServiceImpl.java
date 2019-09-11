package com.example.code.serviceImpl.goods;

import com.example.code.config.TexProperties;
import com.example.code.entity.GoodsOrder;
import com.example.code.mapper.goods.GoodsMapper;
import com.example.code.service.goods.GoodsService;
import com.example.code.utils.CodeMsg;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@CacheConfig(cacheNames = "'goodsList'")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    @Qualifier("goodsMapper")
    //@Resource
    private GoodsMapper goodsMapper;

    @Autowired
    private TexProperties texProperties;

    @Resource
    private RedisTemplate<String,List> redisTemplate;

    @Resource
    private RedisTemplate<String,String> stringRedisTemplate;

    @Override
    @Cacheable(value = "goodsList",key = "#p0+'-'+#p1")
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result selectGoogsList(Map<String, String> paramMap) throws Exception {
        log.info("通过了数据库查询");
        int page = paramMap.get("page")==null?1:Integer.parseInt(paramMap.get("page"));
        int rows = paramMap.get("rows")==null?20:Integer.parseInt(paramMap.get("rows"));
        List goodsList = goodsMapper.selectGoogsList((page-1)*rows,rows);
        log.info("全部的商品列表：{}",goodsList);
        return Result.success(goodsList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    //缓存类型如果是List 不建议使用@CacheEvict
    @CacheEvict(value = "goodsList",allEntries = true)
    public Result updateGoodsInfo(Map<String, String> paramMap) throws Exception {
       String goods_id = paramMap.get("goods_id");
       String goods_name = paramMap.get("goods_name");
       goodsMapper.updateGoodsInfo(goods_id,goods_name);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result buyGoodsForUpdate(Map<String, String> paramMap) throws Exception {
        String goods_id = paramMap.get("goods_id");
        String user_id = paramMap.get("user_id");
        String count = paramMap.get("count");

        //查询库存
        String total_inventory = goodsMapper.selectInventoryByGoodsId(goods_id);
        //String total_inventory = goodsMapper.selectInventoryByGoodsIdAddForUpdate(goods_id);
        log.info("total_inventory:{}", total_inventory);
        int count_int = Integer.parseInt(count);
        int total_inventory_int = Integer.parseInt(total_inventory);
        if (count_int > total_inventory_int) {
            log.info("库存不足");
            return Result.error(CodeMsg.NO_HAVE_INVENTORY,"库存不足");
        } else {
            log.info("添加订单");
            GoodsOrder goodsOrder = new GoodsOrder();
            goodsOrder.setCount(count);
            goodsOrder.setGoodsId(goods_id);
            goodsOrder.setUserId(user_id);
            goodsMapper.addOrderForThisUser(goodsOrder);
            //开始减少库存
            goodsMapper.reduceInventoryByGoodsId(count, goods_id);
        }
        //Thread.sleep(521);

        return Result.success();

    }

    @Override
    public Result forUpdateNoHaveTransation(Map<String, String> paramMap) throws Exception {
       //添加forupdate
        Map goodsMap = goodsMapper.forUpdateNoHaveTransation();
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result buyGoodsRedis(Map<String, String> paramMap) throws Exception {
        ListOperations listOperations = redisTemplate.opsForList();
        String goods_id = paramMap.get("goods_id");
        String user_id = paramMap.get("user_id");
        String count = paramMap.get("count");
        //获取缓存中的值
        int total_inventory_int = Integer.parseInt(stringRedisTemplate.opsForValue().get("total_inventory"));
        log.info("total_inventory_int:{}",total_inventory_int);
        if(listOperations.size("goodsOrder")<=total_inventory_int){
            GoodsOrder goodsOrder = new GoodsOrder();
            goodsOrder.setCount(count);
            goodsOrder.setGoodsId(goods_id);
            goodsOrder.setUserId(user_id);
            listOperations.leftPush("goodsOrder", goodsOrder);
            log.info("goodsOrder的长度{}",listOperations.size("goodsOrder"));
            //异步去新增订单与减少库存
           // AsyncOpsGoodsOrder();
            goodsMapper.addOrderForThisUser(goodsOrder);
            goodsMapper.reduceInventoryByGoodsId(count, goods_id);
        }else{
            log.info("库存不足");
            return Result.error(CodeMsg.NO_HAVE_INVENTORY,"库存不足");
        }
        return Result.success();
    }
    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    public void AsyncOpsGoodsOrder(){
        ListOperations listOperations = redisTemplate.opsForList();
        GoodsOrder goodsOrder = (GoodsOrder)listOperations.rightPop("goodsOrder");
        log.info("开始异步操作数据 {}",goodsOrder.toString());
        goodsMapper.addOrderForThisUser(goodsOrder);
        //开始减少库存
        String count = goodsOrder.getCount();
        String goods_id = goodsOrder.getGoodsId();
        goodsMapper.reduceInventoryByGoodsId(count, goods_id);
    }

}
