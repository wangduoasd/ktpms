package com.kaituo.pms.dao;

import com.kaituo.pms.domain.Integral;
import com.kaituo.pms.domain.IntegralExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IntegralMapper {
    long countByExample(IntegralExample example);

    int deleteByExample(IntegralExample example);

    int deleteByPrimaryKey(Integer integralId);

    int insert(Integral record);

    //试验下下
    Integer insert2(@Param("integralId") Integer integralId, @Param("integralTotal") int integralTotal ,
                           @Param("userId") int userId , @Param("integralChangestr") String integralChangestr ,
                           @Param("integralTime") Date integralTime ,
                           @Param("integralChangeint") int integralChangeint ,
                           @Param("integralStatus") int integralStatus,@Param("integralOperator")String integralOperator);

    int insertSelective(Integral record);

    List<Integral> selectByExample(IntegralExample example);

    Integral selectByPrimaryKey(Integer integralId);

    int updateByExampleSelective(@Param("record") Integral record, @Param("example") IntegralExample example);

    int updateByExample(@Param("record") Integral record, @Param("example") IntegralExample example);

    int updateByPrimaryKeySelective(Integral record);

    int updateByPrimaryKey(Integral record);
}