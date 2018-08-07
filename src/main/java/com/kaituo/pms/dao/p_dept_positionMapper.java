package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_dept_position;
import com.kaituo.pms.bean.p_dept_positionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_dept_positionMapper {
    int countByExample(p_dept_positionExample example);

    int deleteByExample(p_dept_positionExample example);

    int deleteByPrimaryKey(Integer deptPositionId);

    int insert(p_dept_position record);

    int insertSelective(p_dept_position record);

    List<p_dept_position> selectByExample(p_dept_positionExample example);

    p_dept_position selectByPrimaryKey(Integer deptPositionId);

    int updateByExampleSelective(@Param("record") p_dept_position record, @Param("example") p_dept_positionExample example);

    int updateByExample(@Param("record") p_dept_position record, @Param("example") p_dept_positionExample example);

    int updateByPrimaryKeySelective(p_dept_position record);

    int updateByPrimaryKey(p_dept_position record);
}