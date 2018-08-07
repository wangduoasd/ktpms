package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_integral;
import com.kaituo.pms.bean.p_integralExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_integralMapper {
    int countByExample(p_integralExample example);

    int deleteByExample(p_integralExample example);

    int deleteByPrimaryKey(Integer integralId);

    int insert(p_integral record);

    int insertSelective(p_integral record);

    List<p_integral> selectByExample(p_integralExample example);

    p_integral selectByPrimaryKey(Integer integralId);

    int updateByExampleSelective(@Param("record") p_integral record, @Param("example") p_integralExample example);

    int updateByExample(@Param("record") p_integral record, @Param("example") p_integralExample example);

    int updateByPrimaryKeySelective(p_integral record);

    int updateByPrimaryKey(p_integral record);
}