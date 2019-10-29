package com.example.code.service.mybatisplus;

import com.example.code.entity.HhUser;
import com.example.code.entity.HhUser;
import com.example.code.utils.Result;

/**
 * @description: mybatis-plugs接口
 * @create: 2019-10-26 10:54
 **/
public interface MyBatisPlusService {
    Result selectSingleUserInfo(HhUser user)throws Exception;

    Result updateSingleUser(HhUser user)throws Exception;

    Result deleteSingleUser(HhUser user)throws Exception;

    Result selectUserList(HhUser user)throws Exception;
}
