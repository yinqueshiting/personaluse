package com.example.code.config;

import com.example.code.utils.SHA256Util;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: shiro拦截器规则
 * @create: 2019-10-17 09:38
 **/
@Configuration
public class ShiroConfig {

    private final int EXPIRE = 1800;
    //Redis配置
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    /**
     * 首先配置拦截规则
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器. LinkedHashMap保证添加时的顺序
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 可以理解为任何人都可以访问
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        // 如果没有登陆会调用这个接口
        shiroFilterFactoryBean.setLoginUrl("/static/toLogin");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/success");
        //未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("static/unAuthorized");
        //如果要动态的配置接口的权限 可以将接口需要的权限加到数据库或properties(yml)中读取出来 然后循环放到filterChainDefinitionMap中
       // filterChainDefinitionMap.put(perm.get("url"),perm.get("permission"))
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 指定在加密时采用何种加密方式
     * @return
     */
    @Bean
    public ShiroRealm ShiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * 将自己的Realm放到SecurityManager中
     * 后续还可以将Session Cache也放入
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //自定义Session管理
        securityManager.setSessionManager(sessionManager());
        // 自定义Cache实现
       securityManager.setCacheManager(cacheManagers());
        //自定义Realm验证
        securityManager.setRealm(ShiroRealm());
        return securityManager;
    }
    /**
     * 凭证匹配器
     * Shiro 提供了用于加密密码和验证密码服务的 CredentialsMatcher 接口，而 HashedCredentialsMatcher 正是 CredentialsMatcher 的一个实现类。
     * 将密码校验交给Shiro的SimpleAuthenticationInfo进行处理,在这里做匹配配置
     * @Author Sans
     * @CreateTime 2019/6/12 10:48
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用SHA256算法;
        shaCredentialsMatcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        shaCredentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
        return shaCredentialsMatcher;
    }

    /**
     * 开启Shiro-aop注解支持
     * @Attention 使用代理方式所以需要开启代码支持
     * @Author Sans
     * @CreateTime 2019/6/12 8:38
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置Redis管理器
     * @Attention 使用的是shiro-redis开源插件
     * @Author Sans
     * @CreateTime 2019/6/12 11:06
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * 配置Cache管理器
     * 用于往Redis存储权限和角色标识
     * @Attention 使用的是shiro-redis开源插件
     * @Author Sans
     * @CreateTime 2019/6/12 12:37
     */
    @Bean
    public RedisCacheManager cacheManagers() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 配置缓存的话要求放在session里面的实体类必须有个id标识
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }
    /**
     * 配置RedisSessionDAO
     * @Attention 使用的是shiro-redis开源插件
     * @Author Sans
     * @CreateTime 2019/6/12 13:44
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setExpire(EXPIRE);
        return redisSessionDAO;
    }
    /**
     * 配置Session管理器
     * @Author Sans
     * @CreateTime 2019/6/12 14:25
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
}
