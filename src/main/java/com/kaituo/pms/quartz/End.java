package com.kaituo.pms.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/9 11:55
 */
public class End {
    public static void main(String[] args) throws InterruptedException {
        //Allpertask allpertask = allpertaskMapper.findcronbyid (id);// 从数据库查询出来的
        // return searchCron;
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:quartz-context.xml");
        QuartzManager quartzManager=(QuartzManager)context.getBean("quartzManager");

        quartzManager.removeJob( "first",
                "firstGroup",
                "cronTrigger1",
                "triggerGroup1");
    }

}
