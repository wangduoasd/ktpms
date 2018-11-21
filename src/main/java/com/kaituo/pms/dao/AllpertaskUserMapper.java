package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.AllpertaskUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
    List<AllpertaskUser> findAllpertask(Integer user_id);
    List<AllpertaskUser> findpertask1(Integer user_id);
    List<AllpertaskUser> findpertask2(Integer user_id);
    List<AllpertaskUser> findpertask3(Integer user_id);
    /**
     * @return 领取人(分别是谁userid)
     */
    List<Integer> findAllpertask_userid(Integer allpertask_id);



    /**
     * 根据id修改状态（领取任务）
     * @param allpertaskUser
     * @return
     */
    int updateuserbyids(@Param("allpertaskUser")AllpertaskUser allpertaskUser);

    /**
     * 根据id修改状态（放弃任务）
     * @param allpertaskUser
     * @return
     */
    int updateuserbyids2(@Param("allpertaskUser")AllpertaskUser allpertaskUser);
    /**
     * 根据id修改状态（审核驳回）
     * @param allpertaskUser
     * @return
     */
    int updateuserbyids3(@Param("allpertaskUser")AllpertaskUser allpertaskUser);
    /**
     * 查询某条任务状态
     * @param allpertask_id
     * @param user_id
     * @return
     */
   List< AllpertaskUser> findAllpertaskbyids(@Param ("allpertask_id") Integer allpertask_id,
                                       @Param ("user_id") Integer user_id
    );
   AllpertaskUser findAllpertaskbyids1(@Param ("allpertask_id") Integer allpertask_id,
                                              @Param ("user_id") Integer user_id
    );
    /**
     * 查询审核中的任务
     * @return  List<AllpertaskUser>
     */
    List<AllpertaskUser> find_allpertaskfinish();



    /**
     * 任务状态通过审核变为已完成
     * @param allpertaskUser
     */
    void updateTOfinish(@Param("allpertaskUser")AllpertaskUser allpertaskUser);

    /**
     * 任务状态变为审核中有完成时间
     * @param allpertaskUser
     */
    void updateTOnofinish(@Param("allpertaskUser")AllpertaskUser allpertaskUser);

}
