package com.example.code.serviceImpl.springData;

import com.alibaba.fastjson.JSONObject;
import com.example.code.entity.User;
import com.example.code.jpa.UserRepository;
import com.example.code.service.SpringData.UserService;
import com.example.code.utils.RedisUtils;
import com.example.code.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private RedisUtils redisUtils;
    @Override
    //@Cacheable
    public Result findUserById(int id)throws Exception {
        List<User> userList = userRepository.findUserById(id);
        log.info("user_list:"+userList);
        String user = JSONObject.toJSONString(userList);
        redisUtils.set("user",user);
        return Result.success(userList);
    }

    @Override
    public Result findUserReturnMap(String name) {
        List<User> userList = userRepository.findByNameLike(name);
        return Result.success(userList);
    }
}
