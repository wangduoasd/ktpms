package com.kaituo.pms.quartz;


import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.dao.AllpertaskMapper;
import com.kaituo.pms.dao.AllpertaskUserMapper;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.error.MyException;
import com.kaituo.pms.service.AllpertaskService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


/**
 * @author wangduo
 * @date 2018/11/5 - 13:26
 * 任务类
 */
public class ScheduleTask implements Job {

    //注入调度
    @Autowired
    private AllpertaskMapper allpertaskMapper;


    /**
     * Execute the actual job. The job data map will already have been
     * applied as bean property values by execute. The contract is
     * exactly the same as for the standard Quartz execute method.
     */


    @Override
    public void execute(JobExecutionContext jobExecutionContext){

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//        try {
//            allpertaskMapper= (AllpertaskMapper) jobExecutionContext.getScheduler().getContext().get("allpertaskMapper");
//        } catch (SchedulerException e) {
//            e.printStackTrace ();
//        }
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        Integer jobSay1 = dataMap.getInt ("status");
        System.out.println (jobSay1);
        Integer jobSay2 = dataMap.getInt ("task_id");
        System.out.println (jobSay2);
        Allpertask allpertask=new Allpertask ();
        allpertask.setAllpertask_id (jobSay2);
        allpertask.setAllpertask_status (jobSay1);
        for(int i=0;i<10;i++) {
            System.out.println ("-------------------------------------------------");
        }
        try {
//            try {
//              allpertaskService= (AllpertaskService) jobExecutionContext.getScheduler().getContext().get("allpertaskService");
//            } catch (SchedulerException e) {
//                e.printStackTrace ();
//            }
          allpertaskMapper.updateAllpertask (allpertask);
        } catch (MyException e) {
           throw new MyException ("what the hell??");
        }
        // System.out.println("Instance " + key + " of DumbJob says: " + jobSay1 );


    }

}






