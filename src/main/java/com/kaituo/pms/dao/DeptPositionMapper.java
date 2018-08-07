package com.kaituo.pms.dao;

import com.kaituo.pms.bean.DeptPosition;
import com.kaituo.pms.bean.DeptPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptPositionMapper {
    int countByExample(DeptPositionExample example);

    int deleteByExample(DeptPositionExample example);

    int deleteByPrimaryKey(Integer deptPositionId);

    int insert(DeptPosition record);

    int insertSelective(DeptPosition record);

    List<DeptPosition> selectByExample(DeptPositionExample example);

    DeptPosition selectByPrimaryKey(Integer deptPositionId);

    int updateByExampleSelective(@Param("record") DeptPosition record, @Param("example") DeptPositionExample example);

    int updateByExample(@Param("record") DeptPosition record, @Param("example") DeptPositionExample example);

    int updateByPrimaryKeySelective(DeptPosition record);

    int updateByPrimaryKey(DeptPosition record);
}