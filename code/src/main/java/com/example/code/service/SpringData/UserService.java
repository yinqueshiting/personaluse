package com.example.code.service.SpringData;

import com.example.code.entity.User;
import com.example.code.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
   // Result findUserById(int id)throws Exception;
    Result findUserReturnMap(String name);

    Result seelctUserDetails(User userMap)throws Exception;

    Result updateUserDetails(User user)throws Exception;

    Result addUserInfo(User user)throws Exception;
}
