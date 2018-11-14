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
@Transactional(rollbackFor =MyException.class )
public class AllpertaskServiceImpl implements AllpertaskService {
    @Autowired
   private UserMapper userMapper;
    @Autowired
    private   AllpertaskMapper allpertaskMapper;
    @Autowired
    private   AllpertaskUserMapper allpertaskUserMapper;
    @Autowired
    private IntegralMapper integralMapper;
    int i=0;
    ApplicationContext context=new ClassPathXmlApplicationContext ("classpath:quartz-context.xml");
    QuartzManager quartzManager=(QuartzManager)context.getBean("quartzManager");

    /**
     * 添加全员人物
     * @param allpertask
     */
    @Override
    public int distribute_Allpertask(Allpertask allpertask) {
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
        String name=String.valueOf (allpertask.getAllpertask_id ());
        //判断是否需要定时任务
        if(starttime.after (new Date ())){
            SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
            String startCron = formatter.format(starttime);

            try {
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
               return allpertaskid;
    }

    /**
     * 删除全员任务（注释看添加，不再一一赘述   这是一个软删除）
     * @param Allpertask_id
     */
    @Override
    public void delete_Allpertask(Integer Allpertask_id) {
         if(allpertaskUserMapper.findAllpertask_userid (Allpertask_id)!=null){
               throw new MyException (CodeAndMessageEnum.TOKEN_EXPIRED);
         }
         String pertaskName = String.valueOf (Allpertask_id);
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

    /**
     * 查看任务（任务列表）
     * @return
     */
    @Override
    public List<AllpertaskDTO> find_Allpertask_ofadmin() {
        List<AllpertaskDTO> allpertaskDTOList=new ArrayList<> ();
        List<Allpertask> allpertaskList = null;
        try {
            //查询未被删除的全员任务
            allpertaskList = allpertaskMapper.findallpertask ();
        } catch (MyException e) {
           throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
        for (Allpertask allpertask : allpertaskList) {
            AllpertaskDTO allpertaskDTO = new AllpertaskDTO ();
            //将任务信息复制到dto中
            BeanUtils.copyProperties (allpertask, allpertaskDTO);
            List<Integer> userids= null;
            try {
                //查询userid集合
                userids = allpertaskUserMapper.findAllpertask_userid (allpertask.getAllpertask_id ());
            } catch (MyException e) {
                throw new MyException (CodeAndMessageEnum.GETALLPERTASK_COUNT_FAIL);
            }
            List<String> usernames=new ArrayList<> ();
            //查询username，并加入list集合
            for(Integer userid:userids) {
                //这是视图获取的，如果出现人员看不到的话，请更改这里,我调用的是别人的mapper
                //todo 怪我咯
                String username=userMapper.getUserById (userid).getUserName ();
                usernames.add(username);
            }
            allpertaskDTO.setUsername (usernames);
            allpertaskDTOList.add (allpertaskDTO);
        }

        return allpertaskDTOList;
    }

    /**
     * 审核任务列表
     * @return allpertaskDTOList
     */
    @Override
    public List<AllpertaskDTO> find_allpertaskfinish() {
        //查询审核中的任务人员中间表（list）
        List<AllpertaskUser> allpertaskUserList=allpertaskUserMapper.find_allpertaskfinish ();
        List<AllpertaskDTO> allpertaskDTOList=new ArrayList<> ();
        List<String> usernames=new ArrayList<> ();
        //遍历list
        for(AllpertaskUser allpertaskUser:allpertaskUserList){
            AllpertaskDTO allpertaskDTO=new AllpertaskDTO ();
            Integer allpertask_id=allpertaskUser.getAllpertask_id ();
            //查询对应全员任务
            Allpertask allpertask=allpertaskMapper.findallpertaskbyid (allpertask_id);
            //属性复制
            BeanUtils.copyProperties (allpertask,allpertaskDTO);
            //将各种信息放入dto
            allpertaskDTO.setUser_status (allpertaskUser.getUser_status ());
            allpertaskDTO.setUser_gettime (allpertaskUser.getUser_gettime ());
            allpertaskDTO.setUser_finishtime (allpertaskUser.getUser_finishtime ());
            Integer userid= allpertaskUser.getUser_id ();
            //根据id查询username放入集合（这里其实不用集合，但不想加字段了）
            String username=userMapper.getUserById (userid).getUserName ();
            usernames.add(username);
            allpertaskDTO.setUsername (usernames);
            allpertaskDTOList.add (allpertaskDTO);
        }
        return allpertaskDTOList;
    }

    /**
     * 审核通过
     * @param allpertask_id
     * @param userid
     * @return
     */
    @Override
    public void pass_allpertask(int allpertask_id, int userid) {
        AllpertaskUser allpertaskUser = new AllpertaskUser ();
        allpertaskUser.setUser_id (userid);
        allpertaskUser.setUser_status (3);
        allpertaskUser.setAllpertask_id (allpertask_id);
        //修改为状态已完成
        allpertaskUserMapper.updateTOfinish (allpertaskUser);
        //重新添加对应关系
        allpertaskUserMapper.adduser (allpertask_id,userid);
    }

    /**
     * 审核未通过
     * @param allpertask_id
     * @param userid
     * @return
     * @throws InterruptedException
     */
    @Override
    public String fail_allpertask(int allpertask_id, int userid) throws InterruptedException {
         //请求被驳回
        String message="你有全员任务审核没有通过";
        AllpertaskUser allpertaskUser = new AllpertaskUser ();
        allpertaskUser.setAllpertask_id (allpertask_id);
        allpertaskUser.setUser_id (userid);
        allpertaskUser.setUser_status (1);
        Date gettime=new Date ();
        allpertaskUser.setUser_gettime (gettime);
        //改变状态为已领取
        allpertaskUserMapper.updateuserbyids3 (allpertaskUser);
        //定时任务开启
        //根据id查询全员任务信息
        Allpertask allpertask = allpertaskMapper.findallpertaskbyid (allpertask_id);
        long alltaskendtime = allpertask.getAllpertask_endtime ().getTime ();
        long limit=(allpertask.getAllpertask_time ())*3600000;
        long time=gettime.getTime ()+limit;//定时任务任务执行时间
        long timeat=gettime.getTime ()+limit+3000*60;//定时任务结束时间（默认3分钟后）
        String name=String.valueOf (allpertask.getAllpertask_id ());
        if (alltaskendtime > time) {
            Date totime = new Date (time);
            Date endtimeAt = new Date (timeat);
            SimpleDateFormat formatter = new SimpleDateFormat ("ss mm HH dd MM ? yyyy");
            String endCron = formatter.format (totime);
            quartzManager.addjob (
                    name,
                    "领取任务",
                    name,
                    "领取任务",
                    ScheduleTaskPer.class,
                    endCron,
                    0,
                    allpertask_id,
                    endtimeAt,
                    userid
            );
        } else {
            long alltaskendtimeat=alltaskendtime+ 3000 * 60;
            Date totime = new Date (alltaskendtime);
            Date endtimeAt = new Date (alltaskendtimeat);
            SimpleDateFormat formatter = new SimpleDateFormat ("ss mm HH dd MM ? yyyy");
            String endCron = formatter.format (totime);
            quartzManager.addjob (
                    name,
                    "领取任务",
                    name,
                    "领取任务",
                    ScheduleTaskPer.class,
                    endCron,
                    0,
                    allpertask_id,
                    endtimeAt,
                    userid
            );
        }
        return message;
    }



    /**
     * 查看任务(进行中,历史记录,审核中) 【这是用户的】
     * @return allpertaskList
     */

    @Override
    public List<AllpertaskDTO> find_Allpertask_ofuser(int userid,int status) {

            //数据拼接返回给前台
            List<AllpertaskDTO> allpertaskDTOList=new ArrayList<> ();

            List<AllpertaskUser> allpertaskUserList= null;
            try {
                allpertaskUserList = allpertaskUserMapper.findAllpertask (userid);
            } catch (MyException e) {
               throw new MyException (CodeAndMessageEnum.FIND_PERTASKUSER_ERROR);
            }
            for(AllpertaskUser allpertaskUser:allpertaskUserList) {
                switch (status) {
                    case 1://进行中
                        if (allpertaskUser.getUser_status () == 1) {
                            Allpertask allpertask = null;
                            try {
                                allpertask = allpertaskMapper.findallpertaskbyid (allpertaskUser.getAllpertask_id ());
                            } catch (MyException e) {
                                throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
                            }

                            AllpertaskDTO allpertaskDTO = new AllpertaskDTO ();
                            BeanUtils.copyProperties (allpertask, allpertaskDTO);
                            allpertaskDTO.setUser_gettime (allpertaskUser.getUser_gettime ());
                            allpertaskDTO.setUser_status (allpertaskUser.getUser_status ());
                            Date now = new Date ();

                           long resttimes= allpertaskUser.getUser_gettime ().getTime ()
                                   +(allpertask.getAllpertask_time ()*3600000)
                                   -now.getTime ();
                           if(resttimes>0) {
                               Date resttime = new Date (resttimes);
                               allpertaskDTO.setResttime (resttime);
                           }else{
                               long resttime1=allpertask.getAllpertask_endtime ().getTime ()-now.getTime ();
                               Date resttime = new Date (resttime1);
                               allpertaskDTO.setResttime (resttime);
                           }
                            allpertaskDTOList.add (allpertaskDTO);
                        }


                    case 2://审核中
                        if (allpertaskUser.getUser_status () == 2) {
                            Allpertask allpertask = null;
                            try {
                                allpertask = allpertaskMapper.findallpertaskbyid (allpertaskUser.getAllpertask_id ());
                            } catch (MyException e) {
                                throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
                            }

                            AllpertaskDTO allpertaskDTO = new AllpertaskDTO ();
                            BeanUtils.copyProperties (allpertask, allpertaskDTO);
                            allpertaskDTO.setUser_gettime (allpertaskUser.getUser_gettime ());
                            allpertaskDTO.setUser_status (allpertaskUser.getUser_status ());
                            allpertaskDTO.setUser_finishtime (allpertaskUser.getUser_finishtime ());
                            allpertaskDTOList.add (allpertaskDTO);
                        }


                    case 3://已完成
                        if (allpertaskUser.getUser_status () == 3) {
                            Allpertask allpertask = null;
                            try {
                                allpertask = allpertaskMapper.findallpertaskbyid (allpertaskUser.getAllpertask_id ());
                            } catch (MyException e) {
                                throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
                            }
                            AllpertaskDTO allpertaskDTO = new AllpertaskDTO ();
                            BeanUtils.copyProperties (allpertask, allpertaskDTO);
                            allpertaskDTO.setUser_gettime (allpertaskUser.getUser_gettime ());
                            allpertaskDTO.setUser_status (allpertaskUser.getUser_status ());
                            allpertaskDTO.setUser_finishtime (allpertaskUser.getUser_finishtime ());
                            allpertaskDTOList.add (allpertaskDTO);
                        }
                }
            }
                   return allpertaskDTOList;
    }



    /**
     *任务大厅
     * @return
     */
    @Override
    public List<AllpertaskDTO> AllpertaskList() {
        List<AllpertaskDTO> allpertaskDTOList=new ArrayList<> ();
        List<Allpertask> allpertaskList = null;
        try {
            //
            allpertaskList = allpertaskMapper.findallpertask();
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
        for (Allpertask allpertask : allpertaskList) {
            if(allpertask.getAllpertask_status ()<2) {
                AllpertaskDTO allpertaskDTO = new AllpertaskDTO ();
                BeanUtils.copyProperties (allpertask, allpertaskDTO);
                List<Integer> userids = null;
                try {
                    userids = allpertaskUserMapper.findAllpertask_userid (allpertask.getAllpertask_id ());
                } catch (MyException e) {
                    throw new MyException (CodeAndMessageEnum.GETALLPERTASK_COUNT_FAIL);
                }
                List<String> usernames=new ArrayList<> ();
                //查询username，并加入list集合
                for(Integer userid:userids) {
                    //这是视图获取的，如果出现人员看不到的话，请更改这里，我调用的是别人的mapper
                    //todo 怪我咯
                    i++;
                    String username=userMapper.getUserById (userid).getUserName ();
                    usernames.add(username);
                }
                allpertaskDTO.setCount (i);
                allpertaskDTO.setUsername (usernames);
                allpertaskDTOList.add (allpertaskDTO);
            }
        }

        return allpertaskDTOList;
    }

    /**
     * 领取任务
     * @param allpertaskid
     * @param userid
     * @throws InterruptedException
     */
    @Override
    public String get_Allpertask(int allpertaskid, int userid) throws InterruptedException {
        try {
            AllpertaskUser allpertaskUser = new AllpertaskUser ();
            allpertaskUser.setUser_id (userid);
            allpertaskUser.setUser_status (1);
            Date time=new Date();
            allpertaskUser.setUser_gettime (time);
            allpertaskUser.setAllpertask_id (allpertaskid);
            allpertaskUserMapper.updateuserbyids (allpertaskUser);
        } catch (MyException e) {
           String message="您已领取该任务";
           return message;
        }
        //扣除押金
        Allpertask allpertask = null;
        try {
            allpertask = allpertaskMapper.findallpertaskbyid (allpertaskid);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
        //对积分表做减法，需要积分表操作,获取user表中的积分
        User user = null;
        try {
            user = userMapper.selectByPrimaryKey (userid);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.GET_USERINFORMATION_ERROR);
        }
        int integral = user.getUserIntegral ();//当前用户总积分
        int price = allpertask.getAllpertask_price ();//扣除的积分
        int count = integral - price;//扣除后的总积分
        Integral integral1 = new Integral ();
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
        User user1 = new User ();
        user1.setUserIntegral (count);
        UserExample example = new UserExample ();
        example.createCriteria ().andUserIdEqualTo (userid);
        try {
            userMapper.updateByExampleSelective (user1, example);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.UPDATE_INTEGRALUSER_ERROR);
        }
        //定时开始（定时任务为状态变为未领取）
        AllpertaskUser allpertaskUser = null;
        try {
            allpertaskUser = allpertaskUserMapper.findAllpertaskbyids (allpertaskid, userid);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.FIND_PERTASKUSER_ERROR);
        }
        long alltaskendtime = allpertask.getAllpertask_endtime ().getTime ();
        Date gettime = allpertaskUser.getUser_gettime ();
        long limit = (allpertask.getAllpertask_time ()) * 3600000;
        long time = gettime.getTime () + limit;//定时任务任务执行时间
        long timeat = gettime.getTime () + limit + 3000 * 60;//定时任务结束时间（默认3分钟后）
        String name=String.valueOf (allpertask.getAllpertask_id ());
        if (alltaskendtime > time) {
            Date totime = new Date (time);
            Date endtimeAt = new Date (timeat);
            SimpleDateFormat formatter = new SimpleDateFormat ("ss mm HH dd MM ? yyyy");
            String endCron = formatter.format (totime);
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
        } else {
            long alltaskendtimeat=alltaskendtime+ 3000 * 60;
            Date totime = new Date (alltaskendtime);
            Date endtimeAt = new Date (alltaskendtimeat);
            SimpleDateFormat formatter = new SimpleDateFormat ("ss mm HH dd MM ? yyyy");
            String endCron = formatter.format (totime);
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
        }
          return null;
    }

    /**
     * 取消任务
     * @param allpertaskid
     * @param userid
     */
    @Override
    public void giveup_allpertask(int allpertaskid, int userid) {
        try {
            AllpertaskUser allpertaskUser = new AllpertaskUser ();
            allpertaskUser.setAllpertask_id (allpertaskid);
            allpertaskUser.setUser_id (userid);
            allpertaskUser.setUser_status (0);
            allpertaskUserMapper.updateuserbyids2 (allpertaskUser);
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
     */
    @Override
    public void finish_allpertask(int allpertaskid, int userid) {
        try {
            AllpertaskUser allpertaskUser = new AllpertaskUser ();
            allpertaskUser.setAllpertask_id (allpertaskid);
            allpertaskUser.setUser_status (2);
            allpertaskUser.setUser_id (userid);
            allpertaskUserMapper.updateTOnofinish (allpertaskUser);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.GIVEUPALLPERTASK_FAIL);
        }
        Allpertask allpertask= null;
        try {
            allpertask = allpertaskMapper.findallpertaskbyid (allpertaskid);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
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
        allpertaskUserMapper.adduser (allpertaskid,userid);
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
