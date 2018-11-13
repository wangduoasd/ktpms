package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Allpertask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangduo
 * @date 2018/11/5 - 14:40
 */
@Mapper
@Repository
public interface AllpertaskMapper {
    /**
     * 查询任务(未删除的)
     * @param
     * @return
     */
    List<Allpertask> findallpertask();

    /**
     * 添加全员任务
     * @param allpertask
     * @return id
     */
    int addAllpertask(Allpertask allpertask);

    /**
     * 根据id修改全员任务
     * @param allpertask
     * @return
     */
    int updateAllpertask(Allpertask allpertask);

    /**
     * 删除全员任务
     * @param allpertask_id
     * @return
     */
    int deleteAllpertask(Integer allpertask_id);

    /**
     * 根据id查询任务名称
     * @param allpertask_id
     * @return pertaskName
     */
    String findpertaskbyid(Integer allpertask_id);

    /**
     * 根据任务id查询所有信息
     * @param allpertask_id
     * @return
     */
   Allpertask findallpertaskbyid(Integer allpertask_id);

}
