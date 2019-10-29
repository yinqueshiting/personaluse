package com.example.code.jpa;

import com.example.code.entity.HhUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<HhUser,Integer> {
    //@Query(value = "select u from user u where u.name = ?1 ")
    //List<User> findUserById(int id);
    @Query(value = "select user_id,user_name,create_time,sex from hh_user where user_name like CONCAT('%',:name,'%')  ",nativeQuery=true)
    List<HhUser> findByNameLike(@Param("name") String name);

    @Query(value = "select user_name,create_time from hh_user where user_id = :id",nativeQuery = true)
    HhUser selectUserDetails(@Param("id") int user_id);
}
