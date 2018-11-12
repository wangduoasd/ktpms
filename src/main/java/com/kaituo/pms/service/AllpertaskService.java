package com.kaituo.pms.service;


import com.kaituo.pms.DTO.AllpertaskDTO;
import com.kaituo.pms.bean.Allpertask;

import java.util.Date;
import java.util.List;

/**
 * @author wangduo
 * @date 2018/11/6 - 12:59
 */

public interface AllpertaskService {
    //发布任务（管理）
    void distribute_Allpertask(Allpertask allpertask);
     //软删除（管理）
    void delete_Allpertask(Integer Allpertask_id);

    List<AllpertaskDTO> find_Allpertask_ofadmin();
   // void update_Allpertask(Allpertask allpertask);
   //查询任务（含历史记录）（用户）
    List<AllpertaskDTO> find_Allpertask_ofuser(int userid);

    //领取任务
    void get_Allpertask(int allpertaskid,int userid,int status) throws Exception;

    //放弃任务
    void giveup_allpertask(int allpertaskid,int userid,int status);

    //完成任务
    void finish_allpertask(int allpertaskid,int userid,int status);
}
