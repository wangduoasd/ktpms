package com.kaituo.pms.service;

import com.kaituo.pms.bean.Exchange;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ExchangeService {
    List<Exchange> findExchangeRecord(int userId);
    int updateExchange(int userId,int status);
    List<Exchange> selectBykeyWord(String keyWord,int userId);
   List<Exchange> getExchangeLists();
}
