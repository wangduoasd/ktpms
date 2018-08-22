package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.bean.ExchangeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface ExchangeMapper {
    int countByExample(ExchangeExample example);

    int deleteByExample(ExchangeExample example);

    int deleteByPrimaryKey(Integer exchangeId);

    int insert(Exchange record);

    int insertSelective(Exchange record);

    List<Exchange> selectByExample(ExchangeExample example);

    Exchange selectByPrimaryKey(Integer exchangeId);

    int updateByExampleSelective(@Param("record") Exchange record, @Param("example") ExchangeExample example);

    int updateByExample(@Param("record") Exchange record, @Param("example") ExchangeExample example);

    int updateByPrimaryKeySelective(Exchange record);

    int updateByPrimaryKey(Exchange record);

    List<Exchange> findExchangeRecord(@Param("userId") int userId);
    List<Exchange> selectBykeyWord(@Param("keyWord") String keyWord,@Param("userId")int userId);

    List<Exchange> getExchangeLists();
    List<Exchange> selectBykeyWord2(@Param("keyWord") String keyWord);
    String toUserName(@Param("userId") int userId);
    String getPrizeImage(@Param("prizeId") int prizeId);
}