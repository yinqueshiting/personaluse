package com.example.code.mapper.shiro;

import com.example.code.entity.SysMenu;
import com.example.code.entity.SysRole;
import com.example.code.entity.SysUser;
import com.example.code.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Date 2019/10/17 15:21
 * @Created by xg
 */
@Repository
public interface SysUserMapper {
    SysUser selectUserInfo(@Param("username")String username);
    List<SysRole> selectRoleByUserId(@Param("userId")int userId);
    List<SysMenu> selelctMenuByRoleId(@Param("roleId")long roleId);
}
