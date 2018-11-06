package com.kaituo.pms.quartz;

import org.quartz.*;


/**
 * @author wangduo
 * @date 2018/11/5 - 13:26
 * 任务类
 */
public class ScheduleTask  implements Job {
    /**
     * Execute the actual job. The job data map will already have been
     * applied as bean property values by execute. The contract is
     * exactly the same as for the standard Quartz execute method.
     */

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        String jobSays = dataMap.getString("status");

        System.out.println("Instance " + key + " of DumbJob says: " + jobSays );

    }
}


/*        Allpertask allpertask = allpertaskMapper.findcronbyid (id);// 从数据库查询出来的
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String searchCron = formatter.format(allpertask.getAllpertask_starttime ());
        return searchCron;*/



