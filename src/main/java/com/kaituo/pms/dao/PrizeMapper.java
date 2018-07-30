package com.kaituo.pms.dao;

import com.kaituo.pms.domain.Prize;
import com.kaituo.pms.domain.PrizeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrizeMapper {
    long countByExample(PrizeExample example);

    int deleteByExample(PrizeExample example);

    int deleteByPrimaryKey(Integer prizeId);

    int insert(Prize record);

    int insertSelective(Prize record);

    List<Prize> selectByExample(PrizeExample example);

    Prize selectByPrimaryKey(Integer prizeId);

    int updateByExampleSelective(@Param("record") Prize record, @Param("example") PrizeExample example);

    int updateByExample(@Param("record") Prize record, @Param("example") PrizeExample example);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);
      /*模糊查询*/
     List<Prize>  selectBykeyWord(@Param("prizeName") String prizeName);
}