package com.kaituo.pms.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.DTO.AllpertaskDTO;
import com.kaituo.pms.DTO.AllpertaskDTO1;
import com.kaituo.pms.DTO.GetalltaskperDTO;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    private QuartzManager quartzManager;
    @Autowired
   private UserMapper userMapper;
    @Autowired
    private   AllpertaskMapper allpertaskMapper;
    @Autowired
    private   AllpertaskUserMapper allpertaskUserMapper;
    @Autowired
    private IntegralMapper integralMapper;

    int i=0;


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
    public List<AllpertaskDTO1> find_Allpertask_ofadmin() {
          List<AllpertaskDTO1> allpertaskDTO1List=allpertaskMapper.findallpertasktset1 ();
          return allpertaskDTO1List;
//        List<AllpertaskDTO> allpertaskDTOList=new ArrayList<> ();
//        List<Allpertask> allpertaskList = null;
//        try {
//            //查询未被删除的全员任务
//
//            allpertaskList = allpertaskMapper.findallpertask ();
//        } catch (MyException e) {
//           throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
//        }
//        for (Allpertask allpertask : allpertaskList) {
//            AllpertaskDTO allpertaskDTO = new AllpertaskDTO ();
//            //将任务信息复制到dto中
//            BeanUtils.copyProperties (allpertask, allpertaskDTO);
//            List<Integer> userids= null;
//            try {
//                //查询userid集合
//                userids = allpertaskUserMapper.findAllpertask_userid (allpertask.getAllpertask_id ());
//            } catch (MyException e) {
//                throw new MyException (CodeAndMessageEnum.GETALLPERTASK_COUNT_FAIL);
//            }
//            List<GetalltaskperDTO> getalltaskperList = new ArrayList<> ();
//            //查询username，并加入list集合
//            for(Integer userid:userids) {
//                //这是视图获取的，如果出现人员看不到的话，请更改这里,我调用的是别人的mapper
//                //todo 怪我咯
//                String username=userMapper.getUserById (userid).getUserName ();
//                List<AllpertaskUser> allpertaskUserlist =allpertaskUserMapper.findAllpertaskbyids(allpertask.getAllpertask_id (),userid);
//               for(AllpertaskUser allpertaskUser:allpertaskUserlist) {
//                   GetalltaskperDTO getalltaskperDTO = new GetalltaskperDTO ();
//                   getalltaskperDTO.setUserid (userid);
//                   getalltaskperDTO.setUsername (username);
//                   getalltaskperDTO.setGettime (allpertaskUser.getUser_gettime ());
//                   getalltaskperDTO.setFinishtime (allpertaskUser.getUser_finishtime ());
//                   getalltaskperList.add (getalltaskperDTO);
//
//               }
//        }
//            allpertaskDTO.setGetalltaskperList (getalltaskperList);
//            allpertaskDTOList.add (allpertaskDTO);
//        }
//        return allpertaskDTOList;
    }

    /**
     * 审核任务列表
     * @return allpertaskDTOList
     */
    @Override
    public List<AllpertaskDTO1> find_allpertaskfinish() {
        List<AllpertaskDTO1> allpertaskDTO1List=allpertaskMapper.findallpertasktset2 ();
//        //查询审核中的任务人员中间表（list）
//        List<AllpertaskUser> allpertaskUserList=allpertaskUserMapper.find_allpertaskfinish ();
//        List<AllpertaskDTO> allpertaskDTOList=new ArrayList<> ();
//        //遍历list
//        for(AllpertaskUser allpertaskUser:allpertaskUserList){
//            AllpertaskDTO allpertaskDTO=new AllpertaskDTO ();
//            Integer allpertask_id=allpertaskUser.getAllpertask_id ();
//            //查询对应全员任务
//            Allpertask allpertask=allpertaskMapper.findallpertaskbyid (allpertask_id);
//            //属性复制
//            BeanUtils.copyProperties (allpertask,allpertaskDTO);
//            //将各种信息放入dto
//            allpertaskDTO.setUser_status (allpertaskUser.getUser_status ());
//            allpertaskDTO.setUser_gettime (allpertaskUser.getUser_gettime ());
//            allpertaskDTO.setUser_finishtime (allpertaskUser.getUser_finishtime ());
//            Integer userid= allpertaskUser.getUser_id ();
//            //根据id查询username放入集合（这里其实不用集合，但不想加字段了）
//            String username=userMapper.getUserById (userid).getUserName ();
//            List<GetalltaskperDTO> getalltaskperList=new ArrayList<> ();
//            GetalltaskperDTO getalltaskperDTO=new GetalltaskperDTO ();
//            getalltaskperDTO.setUsername (username);
//            getalltaskperList.add (getalltaskperDTO);
//            allpertaskDTO.setGetalltaskperList (getalltaskperList);
//            allpertaskDTOList.add (allpertaskDTO);
//        }
        return allpertaskDTO1List;
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
        //加积分
        Allpertask allpertask= null;
        try {
            allpertask = allpertaskMapper.findallpertaskbyid (allpertask_id);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
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
    public PageInfo find_Allpertask_ofuser(int userid,int status,int pn) {

            List<AllpertaskDTO> allpertaskUserList= null;
                switch (status) {
                    case 1://进行中
                        PageHelper.startPage(pn,10);
                            try {
                                //引入PageHelper分页插件
                                //查询只需要调用,传入的页码，以及每页的大小
                                allpertaskUserList = allpertaskUserMapper.findpertask1(userid);

                            } catch (MyException e) {
                                throw new MyException (CodeAndMessageEnum.FIND_PERTASKUSER_ERROR);
                            }
                        for(AllpertaskDTO allpertaskUser:allpertaskUserList) {
                                Date now = new Date ();
                                long resttimes = allpertaskUser.getUser_gettime ().getTime ()
                                        + (allpertaskUser.getAllpertask_time () * 3600000)
                                        - now.getTime ();
                                if (resttimes > 0) {
                                   int resttime= (int) (resttimes/1000);
                                    allpertaskUser.setResttime (resttime);

                                } else {
                                    long resttime1 = allpertaskUser.getAllpertask_endtime ().getTime () - now.getTime ();
                                    int resttime= (int) (resttime1/1000);
                                    allpertaskUser.setResttime (resttime);
                                }
                            //allpertaskUserList.add (allpertaskUser);
                        }
                        PageInfo page = new PageInfo(allpertaskUserList,5);
                        return page;

                    case 2://审核中
                        PageHelper.startPage(pn,10);
                        try {
                            allpertaskUserList = allpertaskUserMapper.findpertask2 (userid);
                        } catch (MyException e) {
                            throw new MyException (CodeAndMessageEnum.FIND_PERTASKUSER_ERROR);
                        }
                        PageInfo page1 = new PageInfo(allpertaskUserList,5);
                        return page1;

                    case 3://已完成
                        PageHelper.startPage(pn,10);
                        try {
                            allpertaskUserList = allpertaskUserMapper.findpertask3 (userid);
                        } catch (MyException e) {
                            throw new MyException (CodeAndMessageEnum.FIND_PERTASKUSER_ERROR);
                        }
                        PageInfo page2 = new PageInfo(allpertaskUserList,5);
                        return page2;
            }
        return null;
    }



    /**
     *任务大厅
     * @return
     */
    @Override
    public PageInfo AllpertaskList(int pn,int Pagesize) {
        PageHelper.startPage (pn,10);
        List<AllpertaskDTO1> allpertaskDTO1List=allpertaskMapper.findallpertasktset ();
        PageInfo pageInfo=new PageInfo (allpertaskDTO1List);
        return   pageInfo;

        }
    /**
     * 领取任务
     * @param allpertaskid
     * @param userid
     * @throws InterruptedException
     */
    @Override
    public String get_Allpertask(int allpertaskid, int userid) throws InterruptedException {
        Allpertask allpertask = null;
        try {
            allpertask = allpertaskMapper.findallpertaskbyid (allpertaskid);
        } catch (MyException e) {
            throw new MyException (CodeAndMessageEnum.FIND_PERTASK_ERROR);
        }
        try {
            if(allpertask.getAllpertask_status ()!=1){
                String message="任务未开始";
                return message;
            }
            AllpertaskUser allpertaskUser = new AllpertaskUser ();
            allpertaskUser.setUser_id (userid);
            allpertaskUser.setUser_status (1);
            Date time=new Date();
            allpertaskUser.setUser_gettime (time);
            allpertaskUser.setAllpertask_id (allpertaskid);

            AllpertaskUser allpertaskUser2=allpertaskUserMapper.findAllpertaskbyid0(allpertaskid, userid);
           if(allpertaskUser2!=null) {
               allpertaskUserMapper.updateuserbyids (allpertaskUser);
           }else {
               String message="您已领取该任务";
               return message;
           }
        } catch (MyException e) {
           String message="您已领取该任务";
           return message;
        }
        //扣除押金

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
            allpertaskUser = allpertaskUserMapper.findAllpertaskbyids1 (allpertaskid, userid);
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
            Date time=new Date();
            AllpertaskUser allpertaskUser = new AllpertaskUser ();
            allpertaskUser.setAllpertask_id (allpertaskid);
            allpertaskUser.setUser_finishtime (time);
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

    }

    /**
     * 查看领取人
     * @param allpertask_id
     * @return
     */
    @Override
    public  List<GetalltaskperDTO> findpertest(Integer allpertask_id) {
       List<GetalltaskperDTO> getalltaskperDTOList= allpertaskMapper.findpertasktest (allpertask_id);
        return getalltaskperDTOList;
    }


//    @Override
//    public PageInfo findallpertasktest1(int pn, int Pagesize) {
//        PageHelper.startPage (pn,10);
//        List<AllpertaskDTO1> allpertaskDTO1List=allpertaskMapper.findallpertasktset ();
//
//        if (allpertaskDTO1List != null) {
//            for (int i = 0; i < allpertaskDTO1List.size (); i++) {
//                List<GetalltaskperDTO> getalltaskperDTOList = allpertaskMapper.findpertasktest (allpertaskDTO1List.get (i).getAllpertask_id ());
//
//                if (getalltaskperDTOList != null) {
//                    allpertaskDTO1List.get (i).getGetalltaskperDTOS ().addAll (getalltaskperDTOList);
//                }
//            }
//        }
//        PageInfo pageInfo=new PageInfo (allpertaskDTO1List);
//        return  pageInfo;
//
//     }


    // }

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
