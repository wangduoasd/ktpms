package com.kaituo.pms.dao;

import com.kaituo.pms.domain.DeptPosition;
import com.kaituo.pms.domain.DeptPositionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeptPositionMapper {
    long countByExample(DeptPositionExample example);

    int deleteByExample(DeptPositionExample example);

    int insert(DeptPosition record);

    int insertSelective(DeptPosition record);

    List<DeptPosition> selectByExample(DeptPositionExample example);

    int updateByExampleSelective(@Param("record") DeptPosition record, @Param("example") DeptPositionExample example);

    int updateByExample(@Param("record") DeptPosition record, @Param("example") DeptPositionExample example);
}