package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Allpertask;

import java.util.List;

/**
 * @author wangduo
 * @date 2018/11/5 - 14:40
 */
public interface AllpertaskMapper {
    /**
     * 根据任务id查询任务
     * @param
     * @return
     */
    List<Allpertask> findallpertask();

    /**
     * 添加全员任务
     * @param allpertask
     * @return
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
}
