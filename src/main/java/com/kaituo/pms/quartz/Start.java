package com.kaituo.pms.quartz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.stereotype.Component;


/**
 * @author wangduo
 * @date 2018/11/6 - 11:32
 * 整个quattz包是全员任务的定时任务（由于）
 */
@Component
public class Start {


    public static void main(String[] args) throws InterruptedException {


            ApplicationContext context=new ClassPathXmlApplicationContext ("classpath:quartz-context.xml");
            QuartzManager quartzManager=(QuartzManager)context.getBean("quartzManager");
            quartzManager.addjob (
                "first",
                "firstGroup",
                "cronTrigger1",
                "triggerGroup1",
                 ScheduleTask.class,
                "0 42 21 6 11 ?",
                "0"
        );
        quartzManager.addjob (
                "sec",
                "firstGroup",
                "cronTrigger2",
                "triggerGroup1",
                ScheduleTask.class,
                "0/2 * * * * ?",
                "1"
        );
    }


}
