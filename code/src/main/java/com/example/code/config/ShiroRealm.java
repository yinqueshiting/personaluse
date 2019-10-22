package com.example.code.config;

import com.example.code.entity.SysMenu;
import com.example.code.entity.SysRole;
import com.example.code.entity.SysUser;
import com.example.code.entity.SysUserRole;
import com.example.code.service.shiro.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: shiro的认证与授权
 * @create: 2019-10-17 09:40
 **/
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    /**
     * @Description 授权 相当于去查询此人的角色 和 权限
     * @Param
     * @return
     * @Date 2019/10/17 9:42
     * @Created by xg
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        log.info("当前的登陆人:{}", sysUser);
        //保存 角色和权限的 Set集合
        Set<String> roleSet = new HashSet<>();
        Set<String> menuSet = new HashSet<>();
        List<SysRole> roleList = sysUserService.selectRoleByUserId(sysUser.getUserId());
        for (SysRole role:
                roleList) {
            roleSet.add(role.getRoleName());
            List<SysMenu> menuList = sysUserService.selelctMenuByRoleId(role.getRoleId());
            for (SysMenu menu:
                 menuList) {
                menuSet.add(menu.getPerms());
            }
        }
        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setStringPermissions(menuSet);
        authorizationInfo.setRoles(roleSet);
        log.info("查询我权限{}",menuSet);
        log.info("查询我的角色{}",roleSet);
        return authorizationInfo;
    }

    /**
     * @Description 认证 相当于登陆
     * @Param
     * @return
     * @Date 2019/10/17 14:02
     * @Created by xg
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //获取当前登陆人的名称
        String username = (String)authenticationToken.getPrincipal();
        //根据名称去查询此人
        SysUser sysUser = sysUserService.selectUserInfo(username);
        log.info("sysUser:{}", sysUser);
        //判断账号是否存在
        if (sysUser == null) {
            throw new AuthenticationException();
        }
        //判断账号是否被冻结
        if (sysUser.getState()==null||"PROHIBIT".equals(sysUser.getState())){
            throw new LockedAccountException();
        }
        //进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser,                                  //当前用户
                sysUser.getPassword(),                    //密码
                ByteSource.Util.bytes(sysUser.getSalt()), //设置盐值
                getName()
        );
        return authenticationInfo;
    }
}
