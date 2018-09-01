package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.bean.Prize;
import com.kaituo.pms.dao.ExchangeMapper;
import com.kaituo.pms.service.ExchangeService;
import com.kaituo.pms.service.PrizeService;
import com.kaituo.pms.service.UserService;
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
    @Autowired
    PrizeService prizeService;
    @Autowired
    UserService userService;
    @Override
    @Transactional
    public List<Exchange> findExchangeRecord(int userId) {
       return exchangeMapper.findExchangeRecord(userId);
    }

    @Override
    @Transactional
    public  int updateExchange(int exchangeId, int iniStatus,int endStasus){
        Exchange exchange = exchangeMapper.selectByPrimaryKey(exchangeId);
      if(exchange.getExchangeStatus()==iniStatus) {
          exchange.setExchangeStatus(endStasus);
          System.out.println(exchange.toString());
          return exchangeMapper.updateByPrimaryKeySelective(exchange);
      }
      return 2;
    }

    @Override
    @Transactional
    public List<Exchange> selectBykeyWord(String keyWord,int userId) {
        return exchangeMapper.selectBykeyWord(keyWord, userId);
    }

    @Override
    @Transactional
    public List<Exchange> getExchangeLists() {
        return exchangeMapper.getExchangeLists();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Exchange> selectBykeyWord(String keyWord) {
        System.out.println(exchangeMapper.selectBykeyWord2(keyWord));
        return exchangeMapper.selectBykeyWord2(keyWord);

    }
    /**
     　  * @Description: 添加一条兑换记录
     　　* @param prizeId 商品ID,userId 用户ID，num 兑换数量
     　　* @return Exchange
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/17 0017 16:50
     　　*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addexchangeRecord(int prizeId, int userId, int num) {
        Prize prize = prizeService.selectByPrimaryKey(prizeId);
        Exchange exchange = new Exchange();
        exchange.setPrizeId(prizeId);
        exchange.setPrizeName(prize.getPrizeName());
        exchange.setPrizeImage(prize.getPrizeImage());
        exchange.setUserId(userId);
        exchange.setExchangeCount(num);
        exchange.setUserName(userService.findUserById(userId).getUserName());
        exchange.setExchangeTotal(prize.getPrizePrice()*num);
      return   exchangeMapper.insertSelective(exchange);
    }
}
