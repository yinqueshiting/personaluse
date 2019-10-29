package com.example.code.mapper.goods;

import com.example.code.entity.HhUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {
    HhUser selectUserDetails(@Param("user_id")int user_id);

    void updateUserDetails(@Param("user_id") int user_id, @Param("user_name") String user_name);

    void addUserInfo(HhUser user);

    void addUserMenu(@Param("role_id") int role_id, @Param("menu_id")int menu_id);

    void updateUserName(@Param("username") String userU,@Param("user_id") int i);
}
