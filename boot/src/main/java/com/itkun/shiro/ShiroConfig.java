package com.itkun.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
       //添加shiro内置过滤器
        /**
         * shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         *      anon：无需认证（登录）可以访问
         *      authc:必须认证才可以访问
         *      user：如果使用rememberMe的功能可以直接访问
         *      perms：该资源必须得到资源权限才可以访问
         *      role：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap=new LinkedHashMap<String,String>();
        //放行页面
        filterMap.put("/testThymeleaf","anon");
        filterMap.put("/login","anon");
        //授权过滤器
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");
        //所有拦截放最后
        filterMap.put("/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
       //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权的提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        return shiroFilterFactoryBean;
    }


    /**
     * 创建DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建realm
     * @return
     */
    @Bean("userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
