package com.kaituo.pms.service;



import java.util.List;

public interface ExchangeService {

    /**
     *@Description: 根据状态升序查询兑换列表，
     * 并封装到一个List<Map<String,Object>>里面（根据前台页面所需要的数据来进行封装的）
     *@Author: 郭士伟
     *@Date: 2018/7/25
     */
    List<Object> findExchangeByStatusRank();

    /** 
     *@Description: 查询兑换列表的总量
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    long findNumberOfExchange();



    int updateExchangeByID(int exchangeId,int status);

    List<Object> findExchangeBySearchStr(String searchStr);
}
