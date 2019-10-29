package com.example.code.mapper.mybatisplus;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.code.entity.HhUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description: mybatis-plusDao层
 * @create: 2019-10-26 11:01
 **/
@Mapper
@Repository
public interface MyBatisPlusMapper extends BaseMapper<HhUser> {

}
