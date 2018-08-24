package com.kaituo.pms.service;

import com.kaituo.pms.bean.Exchange;

import java.util.List;


public interface ExchangeService {
    List<Exchange> findExchangeRecord(int userId);
    int updateExchange(int exchangeId, int iniStatus,int endStasus);
    List<Exchange> selectBykeyWord(String keyWord,int userId);


   List<Exchange> getExchangeLists();
    List<Exchange> selectBykeyWord(String keyWord);
         /**
            　  * @Description: 添加一条兑换记录
            　　* @param prizeId 商品ID,userId 用户ID，num 兑换数量
            　　* @return Exchange
            　　* @throws
            　　* @author 张金行
            　　* @date 2018/8/17 0017 16:50
            　　*/
    int addexchangeRecord(int prizeId,int userId,int num);

}
