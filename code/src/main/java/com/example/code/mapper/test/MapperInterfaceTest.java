package com.example.code.mapper.test;

import com.example.code.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper()
public interface MapperInterfaceTest {
    @Select("select kmu.name,kmu.phone,kmu.sex from ks_meet_user kmu inner join hh_user hu on hu.user_id = kmu.user_id where kmu.meet_id = #{meet_id}")
    List<User> findUserListByMeetId(String meet_id);
}
