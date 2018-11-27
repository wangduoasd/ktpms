package com.kaituo.pms;

import com.github.pagehelper.PageHelper;
import com.kaituo.pms.quartz.ApplicationStartup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.util.Properties;
@MapperScan(basePackages = "com.kaituo.pms.dao")
@SpringBootApplication
/*@EnableWebSecurity*/
/*@EnableCaching*/
@EnableTransactionManagement
@EnableScheduling//spring 注解执行定时任务:
public class PmsApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PmsApplication.class);
        springApplication.addListeners(new ApplicationStartup ());
        springApplication.run(args);
    }
    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
