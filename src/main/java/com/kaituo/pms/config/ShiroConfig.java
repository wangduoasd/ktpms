package com.kaituo.pms.config;



import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 *@Description: shiro的配置类
 *@Author: 郭士伟
 *@Date: 2018/7/24
*/

@Configuration
public class ShiroConfig {

//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        //拦截器
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
//        //配置不会被拦截的连接，顺序判断
//        filterChainDefinitionMap.put("/static/*","anon");
//        //配置推出过滤器，其中具体代码shiro帮我们实现了
//        filterChainDefinitionMap.put("/logout","logout");
//
//        return null;
//    }
}
