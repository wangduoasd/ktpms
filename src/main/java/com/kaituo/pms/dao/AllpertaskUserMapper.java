package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.AllpertaskUser;

/**
 * @author wangduo
 * @date 2018/11/6 - 14:33
 */
public interface AllpertaskUserMapper {
    /**
     * 添加全员任务id和每个人的id,默认没领取
     * @param userid
     * @param allpertask_id
     * @return
     */
    int adduser(Integer userid,Integer allpertask_id);

    /**
     * 修改领取为获取时间，
     * @param allpertaskUser
     * @return
     */
    int updateuser(AllpertaskUser allpertaskUser);
    /**
     * 删除全员任务
     * @param allpertask_id
     * @return
     */
    int deleteAllpertaskUser(Integer allpertask_id);
    /**
     * 根据userid查询全员任务
     * @param user_id
     * @return
     */
    Allpertask findAllpertask(Integer user_id);

}
