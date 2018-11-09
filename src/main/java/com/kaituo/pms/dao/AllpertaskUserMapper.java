package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.AllpertaskUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangduo
 * @date 2018/11/6 - 14:33
 */
@Mapper
@Repository
public interface AllpertaskUserMapper {
    /**
     * 添加全员任务id和每个人的id,默认没领取
     * @param user_id
     * @param allpertask_id
     * @return
     */
    int adduser(@Param ("allpertask_id") Integer allpertask_id, @Param ("user_id")Integer user_id);
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
