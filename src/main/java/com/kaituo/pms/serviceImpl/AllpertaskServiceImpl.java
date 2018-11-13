//package com.kaituo.pms.serviceImpl;
//
//import com.kaituo.pms.bean.Allpertask;
//import com.kaituo.pms.bean.AllpertaskUser;
//import com.kaituo.pms.bean.User;
//import com.kaituo.pms.dao.AllpertaskMapper;
//import com.kaituo.pms.dao.AllpertaskUserMapper;
//import com.kaituo.pms.dao.UserMapper;
//import com.kaituo.pms.error.MyException;
//import com.kaituo.pms.quartz.QuartzManager;
//import com.kaituo.pms.quartz.ScheduleTask;
//import com.kaituo.pms.service.AllpertaskService;
//import com.kaituo.pms.utils.CodeAndMessageEnum;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author wangduo
// * @date 2018/11/6 - 13:17
// */
//@Service
//public class AllpertaskServiceImpl implements AllpertaskService {
//    @Autowired
//   private UserMapper userMapper;
//    @Autowired
//    private   AllpertaskMapper allpertaskMapper;
//    @Autowired
//    private   AllpertaskUserMapper allpertaskUserMapper;
//    ApplicationContext context=new ClassPathXmlApplicationContext ("classpath:quartz-context.xml");
//    QuartzManager quartzManager=(QuartzManager)context.getBean("quartzManager");
//
//    /**
//     * 添加全员任务
//     * @param allpertask
//     */
//    @Override
//    @Transactional(rollbackFor =MyException.class )
//    public void distribute_Allpertask(Allpertask allpertask) {
//        try {
//            //添加全员任务信息
//            allpertaskMapper.addAllpertask (allpertask);
//
//        } catch (MyException e) {
//            throw new MyException (CodeAndMessageEnum.ADD_PERTASK_ERROR);
//        }
//        //获取添加后返回的taskid
//        int allpertaskid=allpertask.getAllpertask_id ();
//        //获取任务开始时间
//        Date starttime= allpertask.getAllpertask_starttime ();
//        //获取结束时间
//        Date endtime=allpertask.getAllpertask_endtime();
//        //定时任务停止时间
//        long aa=starttime.getTime ()+3000*60;//到任务时间后的三分钟后停止
//        Date endtime1=new Date (aa);
//        long bb=endtime.getTime ()+3000*60;//到任务时间后的三分钟后停止
//        Date endtime2=new Date (bb);
//        System.out.println (endtime1);
//        //判断是否需要定时任务
//        if(starttime.after (new Date ())){
//            SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
//            String startCron = formatter.format(starttime);
//
//            try {
//                quartzManager.addjob (
//                        allpertask.getAllpertask_name (),
//                        "start_jobGroup",
//                        allpertask.getAllpertask_name (),
//                        "start_triggerGroup",
//                        ScheduleTask.class,
//                        startCron,
//                        1,
//                        allpertaskid,
//                        endtime1
//                );
//
//            } catch (InterruptedException e) {
//                e.printStackTrace ();
//                throw new MyException (CodeAndMessageEnum.ADD_JOBSTART_ERROR);
//            }
//        }else{
//            allpertask.setAllpertask_status (1);
//            allpertaskMapper.updateAllpertask (allpertask);
//        }
//        if(endtime.after (new Date ())){
//            SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
//            String endCron = formatter.format(endtime);
//
//            try {
//                quartzManager.addjob (
//                        allpertask.getAllpertask_name (),
//                        "end_jobGroup",
//                        allpertask.getAllpertask_name (),
//                        "end_triggerGroup",
//                        ScheduleTask.class,
//                        endCron,
//                        2,
//                        allpertaskid,
//                        endtime2
//
//                );
//            } catch (InterruptedException e) {
//                e.printStackTrace ();
//                throw new MyException (CodeAndMessageEnum.ADD_JOBEND_ERROR );
//            }
//        }else{
//            throw new MyException (CodeAndMessageEnum.END_TIME_ERROR);
//        }
//        List<Integer> userList= null;
//        try {
//            //获取全部的人员id
//            userList = userMapper.findall ();
//        } catch (MyException e) {
//            throw new MyException(CodeAndMessageEnum.SELECT_USERID_EMPTY);
//        }
//        //遍历人员id
//        for (Integer id:userList) {
//            try {
//                //添加人员id和任务id
//                allpertaskUserMapper.adduser (allpertaskid,id);
//            } catch (MyException e) {
//                throw new MyException (CodeAndMessageEnum.ADD_PERTASKUSER_ERROR);
//            }
//        }
//
//    }
//
//    @Override
//    public void delete_Allpertask(Integer Allpertask_id) {
//
//    }
//
//    /**
//     * 注释看添加，不再一一赘述
//     * @param allpertask
//     */
//    @Override
//    public void update_Allpertask(Allpertask allpertask) {
//     String allpertaskName = allpertask.getAllpertask_name ();
//     Date starttime=allpertask.getAllpertask_starttime ();
//     Date endtime=allpertask.getAllpertask_endtime ();
//        //定时任务停止时间
//        long aa=starttime.getTime ()+3000*60;//到任务时间后的三分钟后停止
//        Date endtime1=new Date (aa);
//        long bb=endtime.getTime ()+3000*60;//到任务时间后的三分钟后停止
//        Date endtime2=new Date (bb);
//        System.out.println (endtime1);
//        if(starttime.after (new Date ())){
//            SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
//            String startCron = formatter.format(starttime);
//            quartzManager.modifyJobTime (
//             allpertaskName,
//            " start_triggerGroup",
//            ScheduleTask.class,
//                    startCron,
//                    endtime1
//            );
//        }else {
//            allpertask.setAllpertask_status (1);
//            allpertaskMapper.updateAllpertask (allpertask);
//        }
//     }
//}
