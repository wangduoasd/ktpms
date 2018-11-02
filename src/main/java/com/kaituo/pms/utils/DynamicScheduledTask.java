package com.kaituo.pms.utils;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class DynamicScheduledTask implements SchedulingConfigurer {
    private String cron = "0/30 * * * * *";
    private int id;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
            taskRegistrar.addTriggerTask(new Runnable() {
                @Override
                public void run() {
                    // 定时任务的业务逻辑
                    System.out.println("提醒打卡");
                }
            }, new Trigger() {
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext) {//设置下次定时器
                    CronTrigger trigger = new CronTrigger(cron); // 定时任务触发，可修改定时任务的执行周期
                    Date nextExecDate = trigger.nextExecutionTime(triggerContext);
                    System.out.println(triggerContext);
                    System.out.println(nextExecDate);
                    return nextExecDate;
                }
            });
    }
    public void setCron(String cron) {
        this.cron = cron;
    }

    public void setId(int id) {
        this.id = id;
    }
}