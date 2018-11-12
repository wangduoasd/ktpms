package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.DTO.AllpertaskDTO;
import com.kaituo.pms.bean.*;
import com.kaituo.pms.dao.AllpertaskMapper;
import com.kaituo.pms.dao.AllpertaskUserMapper;
import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.error.MyException;
import com.kaituo.pms.quartz.QuartzManager;
import com.kaituo.pms.quartz.ScheduleTask;
import com.kaituo.pms.quartz.ScheduleTaskPer;
import com.kaituo.pms.service.AllpertaskService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangduo
 * @date 2018/11/6 - 13:17
 */
@Service
public class AllpertaskServiceImpl implements AllpertaskService {
    @Autowired
   private UserMapper userMapper;
    @Autowired
    private   AllpertaskMapper allpertaskMapper;
    @Autowired
    private   AllpertaskUserMapper allpertaskUserMapper;
    @Autowired
    private IntegralMapper integralMapper;
    ApplicationContext context=new ClassPathXmlApplicationContext ("classpath:quartz-context.xml");
    QuartzManager quartzManager=(QuartzManager)context.getBean("quartzManager");

    /**
     * 添加全员人物
     * @param allpertask
     */
    @Override
    @Transactional(rollbackFor =MyException.class )
    public void distribute_Allpertask(Allpertask allpertask) {
        try {
            //添加全员任务信息
            allpertaskMapper.addAllpertask (allpertask);

        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.ADD_PERTASK_ERROR);
        }
        //获取添加后返回的taskid
        int allpertaskid=allpertask.getAllpertask_id ();
        //获取任务开始时间
        Date starttime= allpertask.getAllpertask_starttime ();
        //获取结束时间
        Date endtime=allpertask.getAllpertask_endtime();
        //定时任务停止时间
        long aa=starttime.getTime ()+3000*60;//到任务时间后的三分钟后停止
        Date endtime1=new Date (aa);
        long bb=endtime.getTime ()+3000*60;//到任务时间后的三分钟后停止
        Date endtime2=new Date (bb);
        System.out.println (endtime1);
        //判断是否需要定时任务
        if(starttime.after (new Date ())){
            SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
            String startCron = formatter.format(starttime);

            try {
                quartzManager.addjob (
                        allpertask.getAllpertask_name (),
                        "start_jobGroup",
                        allpertask.getAllpertask_name (),
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
                        allpertask.getAllpertask_name (),
                        "end_jobGroup",
                        allpertask.getAllpertask_name (),
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
            throw new MyException (CodeAndMessageEnum.END_TIME_ERROR);
        }
        List<Integer> userList= null;
        try {
            //获取全部的人员id
            userList = userMapper.findall ();
        } catch (MyException e) {
            throw new MyException(CodeAndMessageEnum.SELECT_USERID_EMPTY);
        }
        //遍历人员id
        for (Integer id:userList) {
            try {
                //添加人员id和任务id
                allpertaskUserMapper.adduser (allpertaskid,id);
            } catch (MyException e) {
                throw new MyException (CodeAndMessageEnum.ADD_PERTASKUSER_ERROR);
            }
        }

    }

    /**
     * 删除全员任务（注释看添加，不再一一赘述）
     * @param Allpertask_id
     */
    @Override
    public void delete_Allpertask(Integer Allpertask_id) {
         if(allpertaskUserMapper.findAllpertask_count_status (Allpertask_id)>0){
               throw new MyException (CodeAndMessageEnum.TOKEN_EXPIRED);
         }
         String pertaskName = allpertaskMapper.findpertaskbyid (Allpertask_id);


        try {
            quartzManager.removeJob (
                    pertaskName,
                    "start_jobGroup",
                    pertaskName,
                    "start_triggerGroup"
            );
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.DELETE_JOBSTART_ERROR);
        }
        try {
            quartzManager.removeJob (
                    pertaskName,
                    "end_jobGroup",
                    pertaskName,
                    "end_triggerGroup"
            );
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.DELETE_JOBEND_ERROR);
        }
        try {
            allpertaskUserMapper.deleteAllpertaskUser (Allpertask_id);
            allpertaskMapper.deleteAllpertask (Allpertask_id);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.DELETE_PERTASK_ERROR);
        }
    }
//todo 没有抛出异常
    /**
     * 查看任务
     * @return
     */
    @Override
    public List<AllpertaskDTO> find_Allpertask_ofadmin() {
        List<AllpertaskDTO> allpertaskDTOList=new ArrayList<> ();
        List<Allpertask> allpertaskList = null;
        try {
            allpertaskList = allpertaskMapper.findallpertask ();
        } catch (MyException e) {
           throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
        for (Allpertask allpertask : allpertaskList) {
            AllpertaskDTO allpertaskDTO = new AllpertaskDTO ();
            BeanUtils.copyProperties (allpertask, allpertaskDTO);
            Integer count= null;
            try {
                count = allpertaskUserMapper.findAllpertask_count_status (allpertask.getAllpertask_id ());
            } catch (MyException e) {
                throw new MyException (CodeAndMessageEnum.GETALLPERTASK_COUNT_FAIL);
            }
            allpertaskDTO.setCount (count);
            allpertaskDTOList.add (allpertaskDTO);
        }

        return allpertaskDTOList;
    }


    /**
     * 查看任务(全状态穿给前台判断 0未开始，1以开始，2已过期,3已删除')【这是用户的】
     * @return allpertaskList
     */
    @Override
    public List<AllpertaskDTO> find_Allpertask_ofuser(int userid) {

            //数据拼接返回给前台

            List<AllpertaskDTO> allpertaskDTOList=new ArrayList<> ();

            List<AllpertaskUser> allpertaskUserList= null;
            try {
                allpertaskUserList = allpertaskUserMapper.findAllpertask (userid);
            } catch (MyException e) {
               throw new MyException (CodeAndMessageEnum.FIND_PERTASKUSER_ERROR);
            }
            for(AllpertaskUser allpertaskUser:allpertaskUserList){
                    //必须是已领取的
                    if(allpertaskUser.getUser_status ()==1) {
                        //todo
                        //可用多线程
                        //根据用户领取的任务的id进行查询
                        Allpertask allpertask = null;
                        try {
                            allpertask = allpertaskMapper.findallpertaskbyid (allpertaskUser.getAllpertask_id ());
                        } catch (MyException e) {
                            throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
                        }

                        AllpertaskDTO allpertaskDTO = new AllpertaskDTO ();
                            BeanUtils.copyProperties (allpertask, allpertaskDTO);
                            allpertaskDTO.setUser_gettime (allpertaskUser.getUser_gettime ());
                            allpertaskDTO.setAllpertask_status (1);
                            allpertaskDTOList.add (allpertaskDTO);
                    }
            }
            return  allpertaskDTOList;

    }

    /**
     * 领取任务
     * @param allpertaskid
     * @param userid
     * @param status
     * @throws InterruptedException
     */
    @Override
    public void get_Allpertask(int allpertaskid, int userid, int status) throws InterruptedException {
        try {
            allpertaskUserMapper.updateuserbyids (allpertaskid,userid,1);
        } catch (MyException e) {
            throw new MyException(CodeAndMessageEnum.GETALLPERTASK_FAIL);
        }
        //扣除押金
        Allpertask allpertask= null;
        try {
            allpertask = allpertaskMapper.findallpertaskbyid (allpertaskid);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
        //对积分表做减法，需要积分表操作,获取user表中的积分
        User user= null;
        try {
            user = userMapper.selectByPrimaryKey (userid);
        } catch (MyException e) {
            throw new MyException(CodeAndMessageEnum.GET_USERINFORMATION_ERROR);
        }
        int integral= user.getUserIntegral ();//当前用户总积分
        int price=allpertask.getAllpertask_price ();//扣除的积分
        int count=integral-price;//扣除后的总积分
        Integral integral1=new Integral();
        integral1.setUserId (userid);
        integral1.setIntegralStartnum (integral);
        integral1.setIntegralChangestr ("获取全员任务时，要扣除押金");
        integral1.setIntegralChangeint (price);
        integral1.setIntegralEndnum (count);
        try {
            integralMapper.insert (integral1);//添加详细信息
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.UPDATE_INTEGRAL_ERROR);
        }
        User user1=new User();
        user1.setUserIntegral (count);
        UserExample example=new UserExample ();
        example.createCriteria ().andUserIdEqualTo (userid);
        try {
            userMapper.updateByExampleSelective (user1,example);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.UPDATE_INTEGRALUSER_ERROR);
        }
        //定时开始（定时任务为状态变为未领取）
        AllpertaskUser allpertaskUser= null;
        try {
            allpertaskUser = allpertaskUserMapper.findAllpertaskbyids (allpertaskid,userid);
        } catch (MyException e) {
           throw new MyException (CodeAndMessageEnum.FIND_PERTASKUSER_ERROR);
        }
        Date gettime=allpertaskUser.getUser_gettime ();
         long limit=(allpertask.getAllpertask_time ())*3600000;
         long time=gettime.getTime ()+limit;//定时任务任务执行时间
         long timeat=gettime.getTime ()+limit+3000*60;//定时任务结束时间（默认3分钟后）
         Date totime=new Date (time);
        Date endtimeAt=new Date (timeat);
        SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String endCron = formatter.format(totime);
        quartzManager.addjob (
                 allpertask.getAllpertask_name (),
                 "领取任务",
                 allpertask.getAllpertask_name (),
                 "领取任务",
                 ScheduleTaskPer.class,
                 endCron,
                 0,
                 allpertaskid,
                 endtimeAt,
                 userid
         );
    }

    /**
     * 取消任务
     * @param allpertaskid
     * @param userid
     * @param status
     */
    @Override
    public void giveup_allpertask(int allpertaskid, int userid, int status) {
        try {
            allpertaskUserMapper.updateuserbyids (allpertaskid,userid,0);
        } catch (MyException e) {
           throw new MyException (CodeAndMessageEnum.GIVEUPALLPERTASK_FAIL);
        }
        //定时结束(删除)，状态变为未领取
        Allpertask allpertask= null;
        try {
            allpertask = allpertaskMapper.findallpertaskbyid (allpertaskid);
        } catch (MyException e) {
            throw new MyException(CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
        quartzManager.removeJob (
                allpertask.getAllpertask_name (),
                "领取任务",
                allpertask.getAllpertask_name (),
                "领取任务"
        );
    }

    /**
     * 完成任务
     * @param allpertaskid
     * @param userid
     * @param status
     */
    @Override
    public void finish_allpertask(int allpertaskid, int userid, int status) {
        try {
            allpertaskUserMapper.updateuserbyids (allpertaskid,userid,0);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.GIVEUPALLPERTASK_FAIL);
        }
        Allpertask allpertask=allpertaskMapper.findallpertaskbyid (allpertaskid);
        //定时结束，状态变为未领取
        quartzManager.removeJob (
                allpertask.getAllpertask_name (),
                "领取任务",
                allpertask.getAllpertask_name (),
                "领取任务"
        );
        //加对应积分
        User user= null;
        try {
            user = userMapper.selectByPrimaryKey (userid);
        } catch (MyException e) {
          throw new MyException (CodeAndMessageEnum.GET_USERINFORMATION_ERROR);
        }
        int integral= user.getUserIntegral ();//当前用户总积分
        int award=allpertask.getAllpertask_award ();//奖励的积分
        int price=allpertask.getAllpertask_price ();//返还的押金
        int count=integral+award+price;//奖励后后的总积分
        Integral integral1=new Integral();
        integral1.setUserId (userid);
        integral1.setIntegralStartnum (integral);
        integral1.setIntegralChangestr ("完成全员任务时，要奖励积分，返还押金");
        integral1.setIntegralChangeint (award+price);
        integral1.setIntegralEndnum (count);
        try {
            integralMapper.insert (integral1);//添加详细信息
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.UPDATE_INTEGRAL_ERROR);
        }
        User user1=new User();
        user1.setUserIntegral (count);
        UserExample example=new UserExample ();
        example.createCriteria ().andUserIdEqualTo (userid);
        try {
            userMapper.updateByExampleSelective (user1,example);
        } catch (MyException e) {
           throw new MyException (CodeAndMessageEnum.UPDATE_INTEGRALUSER_ERROR);
        }
    }


//    /**
//     * 修改全员任务
//     * 注释看添加，不再一一赘述
//     * @param
//     */
//    @Override
//    public void update_Allpertask(Allpertask allpertask) {
//        try {
//            allpertask.setAllpertask_status (0);//将开始状态县设为0
//            Integer allpertask_id=allpertask.getAllpertask_id ();
//            allpertaskUserMapper.updateuserbyid (allpertask_id);//将人员领取状态设为0
//        } catch (MyException e) {
//            throw new MyException (CodeAndMessageEnum.UPDATE_PERTASKSTATUS_ERROR);
//        }
//        String allpertaskName = allpertask.getAllpertask_name ();
//         Date starttime=allpertask.getAllpertask_starttime ();
//         Date endtime=allpertask.getAllpertask_endtime ();
//        //定时任务停止时间
//        long aa=starttime.getTime ()+3000*60;//到任务时间后的三分钟后停止
//        Date endtime1=new Date (aa);
//        long bb=endtime.getTime ()+3000*60;//到任务时间后的三分钟后停止
//        Date endtime2=new Date (bb);
//        System.out.println (endtime1);
//        if(starttime.after (new Date ())){
//            SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
//            String startCron = formatter.format(starttime);
//            try {
//                quartzManager.modifyJobTime (
//                 allpertaskName,
//                " start_triggerGroup",
//                        startCron,
//                        endtime1
//                );
//            } catch (MyException e) {
//               throw new MyException (CodeAndMessageEnum.UPDATE_JOBSTART_ERROR);
//            }
//        }else {
//            try {
//                allpertask.setAllpertask_status (1);//若时间外设置为1
//                allpertaskMapper.updateAllpertask (allpertask);
//            } catch (MyException e) {
//               throw new MyException (CodeAndMessageEnum.UPDATE_PERTASK_ERROR);
//            }
//        }
//        if(endtime.after (new Date ())){
//            SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
//            String endCron = formatter.format(endtime);
//
//            try {
//                quartzManager.modifyJobTime (
//                        allpertaskName,
//                        " start_triggerGroup",
//                        endCron,
//                        endtime2
//                );
//            } catch (MyException e) {
//                throw new MyException (CodeAndMessageEnum.UPDATE_JOBSTART_ERROR);
//            }
//        }else{
//            throw new MyException (CodeAndMessageEnum.END_TIME_ERROR);
//        }
//
//    }



}
