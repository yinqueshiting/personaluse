package com.example.code.serviceImpl.shiro;

import com.example.code.entity.SysMenu;
import com.example.code.entity.SysRole;
import com.example.code.entity.SysUser;
import com.example.code.entity.SysUserRole;
import com.example.code.mapper.shiro.SysUserMapper;
import com.example.code.service.shiro.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 查询用户实现类
 * @create: 2019-10-17 15:17
 **/
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser selectUserInfo(String username) {
       return sysUserMapper.selectUserInfo(username);
    }

    @Override
    public List<SysRole> selectRoleByUserId(int userId) {
        return sysUserMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<SysMenu> selelctMenuByRoleId(long roleId) {
        return sysUserMapper.selelctMenuByRoleId(roleId);
    }
}
