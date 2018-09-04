package com.kaituo.pms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @author 张金行
 * @version 1.0
 * @Title: MyAdapter
 * @ProjectName pms
 * @Description:
 * @date 2018/9/3 000315:46
 */

@Configuration
public class MyAdapter extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login");
        super.addInterceptors(registry);
    }
}
