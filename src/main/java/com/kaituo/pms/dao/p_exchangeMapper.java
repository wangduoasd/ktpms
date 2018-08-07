package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_exchange;
import com.kaituo.pms.bean.p_exchangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_exchangeMapper {
    int countByExample(p_exchangeExample example);

    int deleteByExample(p_exchangeExample example);

    int deleteByPrimaryKey(Integer exchangeId);

    int insert(p_exchange record);

    int insertSelective(p_exchange record);

    List<p_exchange> selectByExample(p_exchangeExample example);

    p_exchange selectByPrimaryKey(Integer exchangeId);

    int updateByExampleSelective(@Param("record") p_exchange record, @Param("example") p_exchangeExample example);

    int updateByExample(@Param("record") p_exchange record, @Param("example") p_exchangeExample example);

    int updateByPrimaryKeySelective(p_exchange record);

    int updateByPrimaryKey(p_exchange record);
}