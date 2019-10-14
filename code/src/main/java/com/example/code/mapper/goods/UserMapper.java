package com.example.code.mapper.goods;

import com.example.code.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {
    User selectUserDetails(@Param("user_id")int user_id);

    void updateUserDetails(@Param("user_id") int user_id, @Param("user_name") String user_name);

    void addUserInfo(User user);
}
