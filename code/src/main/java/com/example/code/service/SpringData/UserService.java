package com.example.code.service.SpringData;

import com.example.code.entity.HhUser;
import com.example.code.entity.HhUser;
import com.example.code.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
   // Result findUserById(int id)throws Exception;
    Result findUserReturnMap(String name);

    Result seelctUserDetails(HhUser userMap)throws Exception;

    Result updateUserDetails(HhUser user)throws Exception;

    Result addUserInfo(HhUser user)throws Exception;

    Result addUserMenu(int role_id, int menu_id)throws Exception;

    Result updateUserName(String userU, int i)throws Exception;
}
