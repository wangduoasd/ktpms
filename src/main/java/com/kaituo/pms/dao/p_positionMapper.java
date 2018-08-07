package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_position;
import com.kaituo.pms.bean.p_positionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_positionMapper {
    int countByExample(p_positionExample example);

    int deleteByExample(p_positionExample example);

    int deleteByPrimaryKey(Integer positionId);

    int insert(p_position record);

    int insertSelective(p_position record);

    List<p_position> selectByExample(p_positionExample example);

    p_position selectByPrimaryKey(Integer positionId);

    int updateByExampleSelective(@Param("record") p_position record, @Param("example") p_positionExample example);

    int updateByExample(@Param("record") p_position record, @Param("example") p_positionExample example);

    int updateByPrimaryKeySelective(p_position record);

    int updateByPrimaryKey(p_position record);
}