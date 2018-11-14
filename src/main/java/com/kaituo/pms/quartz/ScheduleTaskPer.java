package com.kaituo.pms.quartz;


import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.AllpertaskUser;
import com.kaituo.pms.dao.AllpertaskMapper;
import com.kaituo.pms.dao.AllpertaskUserMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author wangduo
 * @date 2018/11/5 - 13:26
 * 任务类
 */
public class ScheduleTaskPer  implements Job {
    @Autowired
    AllpertaskUserMapper allpertaskUserMapper;
    //注入调度
    private Scheduler scheduler;

    /**
     * Execute the actual job. The job data map will already have been
     * applied as bean property values by execute. The contract is
     * exactly the same as for the standard Quartz execute method.
     */

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobKey key = jobExecutionContext.getJobDetail().getKey();

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        int jobSay1 = dataMap.getInt ("status");
        int jobSay2 = dataMap.getInt ("task_id");
        int JobSay3 = dataMap.getInt ("user_id");
        AllpertaskUser allpertaskUser=new AllpertaskUser ();
        allpertaskUser.setUser_id (JobSay3);
        allpertaskUser.setAllpertask_id (jobSay2);
        allpertaskUser.setUser_status (jobSay1);
        allpertaskUserMapper.updateuserbyids (allpertaskUser);
//        System.out.println("Instance " + key + " of DumbJob says: " + jobSay1 );


    }

}






