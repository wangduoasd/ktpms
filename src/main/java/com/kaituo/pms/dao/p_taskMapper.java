package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_task;
import com.kaituo.pms.bean.p_taskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_taskMapper {
    int countByExample(p_taskExample example);

    int deleteByExample(p_taskExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(p_task record);

    int insertSelective(p_task record);

    List<p_task> selectByExample(p_taskExample example);

    p_task selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") p_task record, @Param("example") p_taskExample example);

    int updateByExample(@Param("record") p_task record, @Param("example") p_taskExample example);

    int updateByPrimaryKeySelective(p_task record);

    int updateByPrimaryKey(p_task record);
}