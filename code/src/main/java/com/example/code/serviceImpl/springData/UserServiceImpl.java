package com.example.code.serviceImpl.springData;

import com.alibaba.fastjson.JSONObject;
import com.example.code.entity.HhUser;
import com.example.code.jpa.UserRepository;
import com.example.code.mapper.goods.UserMapper;
import com.example.code.service.SpringData.UserService;
import com.example.code.utils.RedisUtils;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
//@CacheConfig(cacheNames = "'userDetails'")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private UserMapper userMapper;
    //@Override
    //@Cacheables
   /* public Result findUserById(int id)throws Exception {
        List<User> userList = userRepository.findUserById(id);
        log.info("user_list:"+userList);
        String user = JSONObject.toJSONString(userList);
        redisUtils.set("user",user);
        return Result.success(userList);
    }*/

    @Override
    public Result findUserReturnMap(String name) {
        List<HhUser> userList = userRepository.findByNameLike(name);
        return Result.success(userList);
    }

    /**
     * 使用DTO中的指定参数作为缓存的key
     * @param userMap
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @Cacheable(value = "userDetail",key = "#root.args[0].userId")
    public Result seelctUserDetails(HhUser userMap) throws Exception {
        int user_id = userMap.getUserId();
        if(StringUtils.isEmpty(user_id)){

        }
        HhUser userDetails = userMapper.selectUserDetails(user_id);
        log.info("userDetails:{}",userDetails);
        return Result.success(userDetails);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @CachePut(value = "userDetail",key = "#root.args[0].userId")
    public Result updateUserDetails(HhUser user) throws Exception {
        log.info("修改基本信息");
        int user_id = user.getUserId();
        String user_name = user.getUserName();
        userMapper.updateUserDetails(user_id,user_name);
        return Result.success(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result addUserInfo(HhUser user) throws Exception {
        //用java获取当前时间 是否是当前时区
        LocalDateTime now = LocalDateTime.now();
        log.info("获取本地的时间：{}",now);
        userMapper.addUserInfo(user);
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result addUserMenu(int role_id, int menu_id) throws Exception {
        userMapper.addUserMenu(role_id,menu_id);
        return  Result.success();
    }

    @Override
    public Result updateUserName(String userU, int i) throws Exception {
        userMapper.updateUserName(userU,i);
        return  Result.success();
    }
}