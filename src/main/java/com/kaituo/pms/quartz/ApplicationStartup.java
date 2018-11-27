package com.kaituo.pms.quartz;

import com.kaituo.pms.DTO.AllpertaskDTO;
import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.AllpertaskUser;
import com.kaituo.pms.dao.AllpertaskMapper;
import com.kaituo.pms.dao.AllpertaskUserMapper;
import com.kaituo.pms.error.MyException;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wangduo
 * @date 2018/11/23 - 14:54
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        quarztCheck (contextRefreshedEvent);
    }

/**
 * @Description:   定时任务服务检查
 * @Param: contextRefreshedEvent
 * @return:
 * @Author: 王铎
 * @Date: 2018/11/23
 */
    private void quarztCheck(ContextRefreshedEvent contextRefreshedEvent) {
        QuartzManager quartzManager = contextRefreshedEvent.getApplicationContext().getBean(QuartzManager.class);
        AllpertaskMapper allpertaskMapper = contextRefreshedEvent.getApplicationContext().getBean(AllpertaskMapper.class);
        AllpertaskUserMapper allpertaskUserMapper = contextRefreshedEvent.getApplicationContext().getBean(AllpertaskUserMapper.class);


        List<Allpertask> findallpertask = allpertaskMapper.findallpertask ();
        List<AllpertaskDTO> allpertaskDTOList = allpertaskUserMapper.findpertask00 ();

        for (Allpertask allpertask:findallpertask) {
            int allpertaskid = allpertask.getAllpertask_id ();
            //获取任务开始时间
            Date starttime = allpertask.getAllpertask_starttime ();
            //获取结束时间
            Date endtime = allpertask.getAllpertask_endtime ();
            //定时任务停止时间
            long aa = starttime.getTime () + 3000 * 60;//到任务时间后的三分钟后停止
            Date endtime1 = new Date (aa);
            long bb = endtime.getTime () + 3000 * 60;//到任务时间后的三分钟后停止
            Date endtime2 = new Date (bb);
            System.out.println (endtime1);
            String name = String.valueOf (allpertask.getAllpertask_id ());
            //判断是否需要定时任务
            if (starttime.after (new Date ())) {
                SimpleDateFormat formatter = new SimpleDateFormat ("ss mm HH dd MM ? yyyy");
                String startCron = formatter.format (starttime);

                try {
                    // scheduler.getContext().put("allpertaskService",allpertaskService);
                    quartzManager.addjob (
                            name,
                            "start_jobGroup",
                            name,
                            "start_triggerGroup",
                            ScheduleTask.class,
                            startCron,
                            1,
                            allpertaskid,
                            endtime1
                    );

                } catch (InterruptedException e) {
                    e.printStackTrace ();
                    throw new MyException (CodeAndMessageEnum.ADD_JOBSTART_ERROR);
                }
            }else{
                allpertask.setAllpertask_status (1);
                allpertaskMapper.updateAllpertask (allpertask);
            }
            if(endtime.after (new Date ())){
                SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
                String endCron = formatter.format(endtime);

                try {
                    quartzManager.addjob (
                            name,
                            "end_jobGroup",
                            name,
                            "end_triggerGroup",
                            ScheduleTask.class,
                            endCron,
                            2,
                            allpertaskid,
                            endtime2

                    );
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                    throw new MyException (CodeAndMessageEnum.ADD_JOBEND_ERROR );
                }
            }else{
                allpertask.setAllpertask_status (2);
                allpertaskMapper.updateAllpertask (allpertask);
            }
        }
        for (AllpertaskDTO allpertask:allpertaskDTOList) {
            Integer allpertaskid=allpertask.getAllpertask_id ();
            Integer userid=allpertask.getUser_id ();
            long alltaskendtime = allpertask.getAllpertask_endtime ().getTime ();//任务过期时间
            Date gettime = allpertask.getUser_gettime ();//领取人领取时间
            long limit = (allpertask.getAllpertask_time ()) * 3600000;//时限
            long time = gettime.getTime () + limit;//定时任务任务执行时间
            long timeat = gettime.getTime () + limit + 3000 * 60;//定时任务结束时间（默认3分钟后）
            String name=String.valueOf (allpertask.getAllpertask_id ());
            if(time<System.currentTimeMillis()){
                AllpertaskUser allpertaskUser = new AllpertaskUser ();
                allpertaskUser.setUser_status (0);
                allpertaskUser.setUser_id (allpertask.getUser_id ());
                allpertaskUser.setAllpertask_id (allpertask.getAllpertask_id ());
                allpertaskUserMapper.updateuserbyids2 (allpertaskUser);
            }

               else if (alltaskendtime > time) {
                Date totime = new Date (time);//定时任务任务执行时间
                Date endtimeAt = new Date (timeat);
                SimpleDateFormat formatter = new SimpleDateFormat ("ss mm HH dd MM ? yyyy");
                String endCron = formatter.format (totime);
                try {
                    quartzManager.addjob (
                            name,
                            "领取任务",
                            name,
                            "领取任务",
                            ScheduleTaskPer.class,
                            endCron,
                            0,
                            allpertaskid,
                            endtimeAt,
                            userid
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
           } else {
                long alltaskendtimeat = alltaskendtime + 3000 * 60;
                Date totime = new Date (alltaskendtime);
                Date endtimeAt = new Date (alltaskendtimeat);
                if (alltaskendtimeat < System.currentTimeMillis ()) {
                    Allpertask allpertask1 = new Allpertask ();
                    allpertask1.setAllpertask_status (2);
                    allpertask1.setAllpertask_id (allpertask.getAllpertask_id ());
                    allpertaskMapper.updateAllpertask (allpertask1);
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat ("ss mm HH dd MM ? yyyy");
                    String endCron = formatter.format (totime);
                    try {
                        quartzManager.addjob (
                                name,
                                "领取任务",
                                name,
                                "领取任务",
                                ScheduleTaskPer.class,
                                endCron,
                                0,
                                allpertaskid,
                                endtimeAt,
                                userid
                        );
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
           }
        }
    }
 }

