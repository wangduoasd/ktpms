package com.kaituo.pms.quartz;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author wangduo
 * @date 2018/11/16 - 12:48
 */
@Configuration
public class QuartzConfig {
    @Autowired
    private MyJobFactory jobFactory;

    public QuartzConfig( MyJobFactory jobFactory) {
        this.jobFactory = jobFactory;
    }

    /**
     * l
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        // Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
        SchedulerFactoryBean factory = new SchedulerFactoryBean ();
        // 设置自定义Job Factory，用于Spring管理Job bean
        factory.setJobFactory (jobFactory);
        return factory;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean ().getScheduler ();
    }
}

