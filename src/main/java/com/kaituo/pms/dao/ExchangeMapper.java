package com.kaituo.pms.dao;

import com.kaituo.pms.domain.Exchange;
import com.kaituo.pms.domain.ExchangeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ExchangeMapper {
    long countByExample(ExchangeExample example);

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

    /** 
     *@Description: 自己添加的：根据搜索内容（用户名称或者商品名称）查询对应的兑换列表
     *@Param: 
     *@return: 
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    List<Exchange> selectBySearch(String searchStr);
    
    /** 
    * @Description:  自己添加的：根据搜索内容（用户名称或者商品名称）和员工id查询对应的兑换列表数据总数
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    int findNumberOfExchangeByUserIDAndSearchStr(@Param("userID") int userID,@Param("prizeID")String prizeID);

    /**
     * @Description:  自己添加的：根据搜索内容（用户名称或者商品名称）和员工id查询对应的兑换列表数据
     * @Param:
     * @return:
     * @Author: su
     * @Date: 2018/7/27
     */
    List<Exchange> findExchangeByUserIDAndSearchStr(@Param("userID") int userID,@Param("prizeID")String prizeID);


}