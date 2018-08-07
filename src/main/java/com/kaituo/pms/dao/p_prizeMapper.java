package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_prize;
import com.kaituo.pms.bean.p_prizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_prizeMapper {
    int countByExample(p_prizeExample example);

    int deleteByExample(p_prizeExample example);

    int deleteByPrimaryKey(Integer prizeId);

    int insert(p_prize record);

    int insertSelective(p_prize record);

    List<p_prize> selectByExample(p_prizeExample example);

    p_prize selectByPrimaryKey(Integer prizeId);

    int updateByExampleSelective(@Param("record") p_prize record, @Param("example") p_prizeExample example);

    int updateByExample(@Param("record") p_prize record, @Param("example") p_prizeExample example);

    int updateByPrimaryKeySelective(p_prize record);

    int updateByPrimaryKey(p_prize record);
}