package com.kaituo.pms.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author wangduo
 * @date 2018/11/16 - 11:13
 */
@Component
public class MyJobFactory extends AdaptableJobFactory {
            @Autowired
            private AutowireCapableBeanFactory beanFactory;
            public MyJobFactory(AutowireCapableBeanFactory factory) {
                this.beanFactory = factory;
            }
            public MyJobFactory(){}
        /**

      * 这里覆盖了super的createJobInstance方法，对其创建出来的类再进行autowire。

      */
        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
             Object jobInstance = super.createJobInstance(bundle);
             beanFactory.autowireBean(jobInstance);
             return jobInstance;
        }
 }