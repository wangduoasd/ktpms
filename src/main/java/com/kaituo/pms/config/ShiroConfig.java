package com.kaituo.pms.config;



import com.kaituo.pms.shiro.MyShiroRealm;
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

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        //Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*过滤链定义
        * 从上向下顺序执行，一般将“/**”放在最下面
        * anon:游客模式，所有url都可以匿名访问
        * authc：登陆模式，所有url都必须经过认证才能访问
        * */
        //配置不会被拦截的连接，顺序判断
        //filterChainDefinitionMap.put("/static/*","anon");
        //配置推出过滤器，其中具体代码shiro帮我们实现了
        //filterChainDefinitionMap.put("/logout","logout");
        //filterChainDefinitionMap.put("/**","authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //shiroFilterFactoryBean.setLoginUrl("/login");
        //登陆成功后跳转的页面
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        //将刚刚封装的过滤连map，set到ShiroFilterFactoryBean
        //shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 自己定义的realm
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }


    /**
     * 给SecurityManage设置需要管理的realm，可以有多个realm
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

}
