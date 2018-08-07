package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_dept;
import com.kaituo.pms.bean.p_deptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_deptMapper {
    int countByExample(p_deptExample example);

    int deleteByExample(p_deptExample example);

    int deleteByPrimaryKey(Integer deptId);

    int insert(p_dept record);

    int insertSelective(p_dept record);

    List<p_dept> selectByExample(p_deptExample example);

    p_dept selectByPrimaryKey(Integer deptId);

    int updateByExampleSelective(@Param("record") p_dept record, @Param("example") p_deptExample example);

    int updateByExample(@Param("record") p_dept record, @Param("example") p_deptExample example);

    int updateByPrimaryKeySelective(p_dept record);

    int updateByPrimaryKey(p_dept record);
}