//package com.kaituo.pms.quartz;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Component;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
///**
// * @author wangduo
// * @date 2018/11/6 - 11:32
// * 整个quattz包是全员任务的定时任务（由于1.0不是很熟知所以独立出来）
// */
//@Component
//public class  Start {
//    public static void main(String[] args) throws InterruptedException {
//        //Allpertask allpertask = allpertaskMapper.findcronbyid (id);// 从数据库查询出来的
//        // return searchCron;
//        ApplicationContext context=new ClassPathXmlApplicationContext ("classpath:quartz-context.xml");
//        QuartzManager quartzManager=(QuartzManager)context.getBean("quartzManager");
//            quartzManager.addjob (
//                "first",
//                "firstGroup",
//                "cronTrigger1",
//                "triggerGroup1",
//                 ScheduleTask.class,
//                    "0/1 * * * * ?",
//                0,
//                    1,
//                    new Date(118,10,9,12,0,0)
//
//        );
//        quartzManager.modifyJobTime(
//                "cronTrigger1",
//                "triggerGroup1",
//                "0/5 * * * * ?",
//                new Date(118,10,9,12,0,0)
//        );
//        quartzManager.removeJob( "first",
//                "firstGroup",
//                "cronTrigger1",
//                "triggerGroup1");
//    }
//
//
//}
