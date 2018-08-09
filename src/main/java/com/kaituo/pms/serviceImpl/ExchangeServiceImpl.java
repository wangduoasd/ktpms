package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.bean.ExchangeExample;
import com.kaituo.pms.dao.ExchangeMapper;
import com.kaituo.pms.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: ExchangeServiceImpl
 * @ProjectName pms
 * @Description:
 * @date 2018/8/8 000811:10
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    ExchangeMapper exchangeMapper;

    @Override
    @Transactional
    public List<Exchange> findExchangeRecord(int userId) {
       return exchangeMapper.findExchangeRecord(userId);
    }

    @Override
    @Transactional
    public int updateExchange(int exchangeId) {
        Exchange exchange = new Exchange();
        exchange.setExchangeId(exchangeId);
        exchange.setExchangeStatus(3);
        return exchangeMapper.updateByPrimaryKeySelective(exchange);
    }

    @Override
    @Transactional
    public List<Exchange> selectBykeyWord(String keyWord) {
        return exchangeMapper.selectBykeyWord(keyWord);
    }
}
