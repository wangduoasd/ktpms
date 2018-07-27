package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.ExchangeMapper;
import com.kaituo.pms.dao.PrizeMapper;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.domain.*;
import com.kaituo.pms.service.ExchangeService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.thymeleaf.expression.Ids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    ExchangeMapper exchangeMapper;
    @Autowired
    PrizeMapper prizeMapper;
    @Autowired
    UserMapper userMapper;
    /**
     *@Description: 按照状态升序查询所有商品兑换列表
     *@Param:
     *@return:
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    @Override
    public List<Object> findExchangeByStatusRank() {
        ExchangeExample example = new ExchangeExample();
        example.setOrderByClause("exchange_status asc");
        List<Exchange> list = exchangeMapper.selectByExample(example);
        List<Object> listExchangeMap = new ArrayList<>();
       for(int i=0;i<list.size();i++){
           Map<String,Object> map = new HashMap<>();
           Prize prize = prizeMapper.selectByPrimaryKey(list.get(i).getPrizeId());
           User user = userMapper.selectByPrimaryKey(list.get(i).getUserId());
           map.put("echangeId",list.get(i).getExchangeId());
           map.put("prizeImage",prize.getPrizeImage());
           map.put("prizeName",prize.getPrizeName());
           map.put("exchangeCount",list.get(i).getExchangeCount());
           map.put("echangeTime",list.get(i).getExchangeTime());
           map.put("userName",user.getUserName());
           map.put("exchangeStatus",list.get(i).getExchangeStatus());
           listExchangeMap.add(map);
       }

        return listExchangeMap;
    }

    /**
     *@Description: 查询商品兑换列表的总数量
     *@Param:
     *@return:
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    @Override
    public long findNumberOfExchange() {
        return exchangeMapper.countByExample(null);
    }

    /**
     *@Description: 根据id更新兑换列表信息的状态
     *@Param:
     *@return:
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    @Override
    public int updateExchangeByID(int exchangeId,int status) {
        ExchangeExample example = new ExchangeExample();
        ExchangeExample.Criteria criteria = example.createCriteria();
        criteria.andExchangeIdEqualTo(exchangeId);
        Exchange e = new Exchange();
        e.setExchangeStatus(2);
        return exchangeMapper.updateByExampleSelective(e,example);
    }

    /**
     *@Description: 根据搜索内容查询兑换列表
     *@Param:
     *@return:
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    @Override
    public List<Object> findExchangeBySearchStr(String searchStr) {

        List<Exchange> list = exchangeMapper.selectBySearch(searchStr);
        List<Object> listExchangeMap = new ArrayList<>();

        if(list.size() >= 0 && list != null){
            for(int i=0;i<list.size();i++){
                Map<String,Object> map = new HashMap<>();
                Prize prize = prizeMapper.selectByPrimaryKey(list.get(i).getPrizeId());
                User user = userMapper.selectByPrimaryKey(list.get(i).getUserId());
                map.put("echangeId",list.get(i).getExchangeId());
                map.put("prizeImage",prize.getPrizeImage());
                map.put("prizeName",prize.getPrizeName());
                map.put("exchangeCount",list.get(i).getExchangeCount());
                map.put("echangeTime",list.get(i).getExchangeTime());
                map.put("userName",user.getUserName());
                map.put("exchangeStatus",list.get(i).getExchangeStatus());
                listExchangeMap.add(map);
            }
            return listExchangeMap;
        }
        return null;
    }
}
