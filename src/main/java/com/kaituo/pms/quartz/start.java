package com.kaituo.pms.quartz;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * @author wangduo
 * @date 2018/11/6 - 11:32
 * 整个quattz包是全员任务的定时任务（由于）
 */
public class start {
    public static void main(String[] args) {
            ApplicationContext context=new ClassPathXmlApplicationContext ("classpath:quartz-context.xml");
            QuartzManager quartzManager=(QuartzManager)context.getBean("quartzManager");
            quartzManager.addjob (
                "first",
                "firstGroup",
                "cronTrigger1",
                "triggerGroup1",
                 ScheduleTask.class,
                "0/1 * * * * ?",
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
