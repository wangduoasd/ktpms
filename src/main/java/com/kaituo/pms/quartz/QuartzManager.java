package com.kaituo.pms.quartz;

import com.kaituo.pms.error.MyException;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import lombok.Data;
import org.quartz.*;

import java.util.Date;


/**
 * @author wangduo
 * @date 2018/11/5 - 20:17
 */
@Data
public class QuartzManager {

    //注入调度
    private Scheduler scheduler;

    //新增定时任务
    @SuppressWarnings ({"unchecked","rawtypes"})
    public  void addjob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
                              Class jobClass, String cron, int status,int allpertaskid,Date endtime ) throws InterruptedException {
        try {
            //1.指定要运行的任务，设置任务名、任务组名
            JobDetail jobDetail=JobBuilder.newJob (jobClass)
                    .withIdentity (jobName,jobGroupName)
                    .usingJobData ("status",status)
                    .usingJobData ("task_id",allpertaskid)
                    .build ();
            //2.配置触发器
            //a.新建一个触发器构造类
            TriggerBuilder<Trigger> triggerBuilder=TriggerBuilder.newTrigger ();
            //b.设置触发器名、触发组名
            triggerBuilder.withIdentity (triggerName,triggerGroupName);
            triggerBuilder.startNow ();
            //long now = System.currentTimeMillis ();
            //todo
          //  String closeTime = "40";
          triggerBuilder.endAt(endtime);
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

            scheduler.start ();
        }catch (SchedulerException e){
            e.printStackTrace ();
        }
    }
    //修改定时任务cron表达式
    public void modifyJobTime( String triggerName, String triggerGroupName,
                         Class jobClass, String cron,Date endtime){
         try{
             TriggerKey triggerKey=TriggerKey.triggerKey (triggerName,triggerGroupName);
             CronTrigger trigger= (CronTrigger) scheduler.getTrigger (triggerKey);
             if(trigger==null){
                 throw new MyException (CodeAndMessageEnum.TRIGGER_EMPTY);
             }
             String oldtime=trigger.getCronExpression ();
             if(!oldtime.equalsIgnoreCase (cron)){
                 TriggerBuilder<Trigger> triggerBuilder=TriggerBuilder.newTrigger ();
                 triggerBuilder.withIdentity (triggerName,triggerGroupName);
                 triggerBuilder.startNow ();
                 triggerBuilder.endAt (endtime);
                 triggerBuilder.withSchedule (CronScheduleBuilder.cronSchedule (cron));
                 trigger= (CronTrigger) triggerBuilder.build ();
                 scheduler.rescheduleJob (triggerKey,trigger);
             }
         }catch (SchedulerException e){
             e.printStackTrace ();
         }
    }
    //删除定时任务
    public void  removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName){
        try{
            TriggerKey triggerKey=TriggerKey.triggerKey (triggerName,triggerGroupName);
            scheduler.pauseTrigger (triggerKey);//停止触发器
            scheduler.unscheduleJob (triggerKey);//移除触发器
            scheduler.deleteJob (JobKey.jobKey (jobName,jobGroupName));
        }catch (Exception e){
           throw new RuntimeException (e);
        }
    }
    //启动所有的定时任务

    public void startJobs() throws InterruptedException {
        try{
            scheduler.start ();

        }catch (Exception e){
            throw new RuntimeException (e);
        }
    }
    //停止定时任务
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
