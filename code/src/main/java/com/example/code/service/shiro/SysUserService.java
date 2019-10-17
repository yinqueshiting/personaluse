package com.example.code.service.shiro;

import com.example.code.entity.SysMenu;
import com.example.code.entity.SysRole;
import com.example.code.entity.SysUser;
import com.example.code.entity.SysUserRole;

import java.util.List;

/**
 * @Description TODO
 * @Date 2019/10/17 15:15
 * @Created by xg
 */
public interface SysUserService {
   /**
    * @Description 根据名称查询用户详情
    * @Param
    * @return
    * @Date 2019/10/17 16:09
    * @Created by xg
    */
    SysUser selectUserInfo(String username);
    /**
     * @Description 根据user_id查询角色列表
     * @Param
     * @return
     * @Date 2019/10/17 16:09
     * @Created by xg
     */
    List<SysRole> selectRoleByUserId(int userId);

    /**
     * @Description 根据role_id查询权限啊列表
     * @Param
     * @return
     * @Date 2019/10/17 16:37
     * @Created by xg
     */
    List <SysMenu> selelctMenuByRoleId(long roleId);
}
