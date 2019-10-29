package com.example.code.serviceImpl.mybatisplus;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.code.entity.HhUser;
import com.example.code.entity.HhUser;
import com.example.code.mapper.mybatisplus.MyBatisPlusMapper;
import com.example.code.service.mybatisplus.MyBatisPlusService;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: mybatis-plugs实现类
 * @create: 2019-10-26 10:54
 **/
@Service("s1")
@Slf4j
public class MyBatisPlusServiceImpl implements MyBatisPlusService {
    @Autowired
    private MyBatisPlusMapper myBatisPlusMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result selectSingleUserInfo(HhUser user) throws Exception {
       user.setUserId(1006);
        EntityWrapper entityWrapper = new EntityWrapper(user);
        HhUser count =  myBatisPlusMapper.selectOne(user);
        log.info("singUser:{}", count);
        return Result.success(count);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateSingleUser(HhUser user) throws Exception {
        //要修改的字段
        user.setCreateTime("2019-10-26 20:25:25");
        user.setUserName("什么名称");
        HhUser updateUser = new HhUser();
        updateUser.setUserId(1006);
        EntityWrapper<HhUser> entityWrapper = new EntityWrapper(updateUser);
        Integer count = myBatisPlusMapper.update(user, entityWrapper);
        log.info("受影响行数：{}", count);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result deleteSingleUser(HhUser user) throws Exception {
        HhUser oneUser = new HhUser();
        oneUser.setUserName("名字一样的");
        HhUser twoUser = new HhUser();
        twoUser.setUserId(1016);
        Integer count = myBatisPlusMapper.delete(new EntityWrapper(oneUser));
        log.info("受影响行数", count);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result selectUserList(HhUser user) throws Exception {
        Page<HhUser> page = new Page<>(0, 10);
        EntityWrapper<HhUser> entityWrapper = new EntityWrapper<>(user);
        entityWrapper.ge("user_id", 5);
        entityWrapper.orderBy("user_id", false);
        List<HhUser> userList = myBatisPlusMapper.selectPage(page, entityWrapper);
        log.info("查询出来的list:{}", userList.size());
        return Result.success(userList);
    }
}
