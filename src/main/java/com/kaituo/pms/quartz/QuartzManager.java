package com.kaituo.pms.quartz;

import com.kaituo.pms.config.WebMvcConf;
import lombok.Data;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wangduo
 * @date 2018/11/5 - 20:17
 */
@Data
public class QuartzManager {
   // 注入调度
    private Scheduler scheduler;


    //新增定时任务
    @SuppressWarnings ({"unchecked","rawtypes"})
    public  void addjob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
                              Class jobClass, String cron, String status) throws InterruptedException {
        try {
            //1.指定要运行的任务，设置任务名、任务组名
            JobDetail jobDetail=JobBuilder.newJob (jobClass)
                    .withIdentity (jobName,jobGroupName)
                    .usingJobData ("status",status)
                    .build ();
            //2.配置触发器
            //a.新建一个触发器构造类
            TriggerBuilder<Trigger> triggerBuilder=TriggerBuilder.newTrigger ();
            //b.设置触发器名、触发组名
            triggerBuilder.withIdentity (triggerName,triggerGroupName);
            triggerBuilder.startNow ();
            //c.设置触发器的时间规则
            triggerBuilder.withSchedule (CronScheduleBuilder.cronSchedule (cron));
            //d.用触发器构造类创建trigger对象
            CronTrigger trigger =(CronTrigger)triggerBuilder.build ();
            //3.把上面 创建好的任务和触发器注册到调度器中
            scheduler.scheduleJob (jobDetail,trigger);
            //启动调度器
            if(!scheduler.isShutdown ()){
                scheduler.start ();

            }
        }catch (SchedulerException e){
            e.printStackTrace ();
        }
    }
    //启动所有的定时任务
  //  @PostConstruct
    public void startJobs() throws InterruptedException {
        try{
            scheduler.start ();

        }catch (Exception e){
            throw new RuntimeException (e);
        }
    }
    public void shutdownJobs(){
        try {
            if(!scheduler.isShutdown ()){
                scheduler.shutdown ();
            }
        }catch (Exception e){
            throw new RuntimeException (e);
        }
    }
}
