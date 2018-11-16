package com.kaituo.pms.service;


import com.kaituo.pms.DTO.AllpertaskDTO;
import com.kaituo.pms.bean.Allpertask;
import org.quartz.SchedulerException;

import java.util.Date;
import java.util.List;

/**
 * @author wangduo
 * @date 2018/11/6 - 12:59
 */

public interface AllpertaskService {
    //发布任务（管理）
    int distribute_Allpertask(Allpertask allpertask);
     //软删除（管理）
    void delete_Allpertask(Integer Allpertask_id);
     //查询任务（管理）（过期和没过期的，已领取和未领取）
    List<AllpertaskDTO> find_Allpertask_ofadmin();
    //审核任务列表
    List<AllpertaskDTO> find_allpertaskfinish();
    //审核通过
    void pass_allpertask(int allpertask_id,int userid);//重新创建全员任务与人员的关系
    //审核驳回
    String fail_allpertask(int allpertask_id,int userid) throws InterruptedException;//变回正在已领取，返回message（被驳回）
   // void update_Allpertask(Allpertask allpertask);
   //查询任务（进行中,历史记录）（用户）status用于判断（进行中,历史记录）
    List<AllpertaskDTO> find_Allpertask_ofuser(int userid,int status);
    //任务大厅
    List<AllpertaskDTO> AllpertaskList();
    //领取任务
    String get_Allpertask(int allpertaskid,int userid) throws Exception;

    //放弃任务
    void giveup_allpertask(int allpertaskid,int userid);

    //完成任务
    void finish_allpertask(int allpertaskid,int userid);

}
