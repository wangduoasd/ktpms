package com.kaituo.pms.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.kaituo.pms.dao.ExchangeMapper;
import com.kaituo.pms.dao.PrizeMapper;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.domain.*;
import com.kaituo.pms.service.ExchangeService;
import com.kaituo.pms.service.PrizeService;
import com.kaituo.pms.utils.MapUtil;
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
    @Autowired
    PrizeService prizeService;
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

    /** 
    * @Description:  查询普通员工商品兑换记录总数
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    @Override
    public long findNumberOfExchangeByUserID(Integer userID) {
        ExchangeExample example = new ExchangeExample();
        ExchangeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        return exchangeMapper.countByExample(example);
    }
    /** 
    * @Description: 查询普通员工商品兑换记录
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    @Override
    public List<Exchange> findExchangeByUserID(Integer userID) {
        ExchangeExample example = new ExchangeExample();
        ExchangeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        return exchangeMapper.selectByExample(example);
    }

    @Override
    public List<Map> reconstituteUsers(List<Exchange> list) {
        List<Map> userMapList = new ArrayList<>();
        for (Exchange exchange:list){
            Map<String , Object> prizeMap = new HashMap<>(6);
            Prize prize = prizeService.findPrizeByID(exchange.getPrizeId());
            prizeMap.put("prizeImage" , prize.getPrizeImage());
            prizeMap.put("prizeName" , prize.getPrizeName());
            prizeMap.put("prizePrice" , prize.getPrizePrice());
            prizeMap.put("prizeAmount" , exchange.getExchangeCount());
            prizeMap.put("exchangePrice" , exchange.getExchangePrice());
            prizeMap.put("exchangeTime" , exchange.getExchangeTime());
            userMapList.add(prizeMap);
        }
        return userMapList;
    }

    @Override
    public Map<String, Object> findExchangeRecordByUser(String pageNumber, String pageSize,Integer userID) {
        Integer intPageNumber = Integer.parseInt(pageNumber);
        Integer intPpageSize = Integer.parseInt(pageSize);
        //查询所有普通员工的兑换记录总数
        int total = (int)findNumberOfExchangeByUserID(userID);
        Map<String,Object> map;
        if (0<total){
            //拥有记录的情况
            //分页
            PageHelper.startPage(intPageNumber,intPpageSize);
            List<Exchange> exchangeList = findExchangeByUserID(userID);
            //重组数据
            List<Map> exchangeMapList = reconstituteUsers(exchangeList);
            //封装map
            Map<String,Object> data = new HashMap<>(2);

            //员工的总数
            data.put("total",total);
            //员工的信息
            data.put("Exchange",exchangeMapList);
            map = MapUtil.setMap2("1","成功",data);
        } else{
            map = MapUtil.setMap2("0","未找到相应兑换记录",null);
        }
        return map;
    }

    @Override
    public long findNumberOfExchangeByUserIDAndSearchStr(Integer userID, String searchStr) {
        return exchangeMapper.findNumberOfExchangeByUserIDAndSearchStr(userID,searchStr);
    }

    @Override
    public List<Exchange> findExchangeByUserIDAndSearchStr(Integer userID, String searchStr) {
        return exchangeMapper.findExchangeByUserIDAndSearchStr(userID,searchStr);
    }

    @Override
    public Map<String, Object> searchExchangeRecord(String pageNumber, String pageSize, Integer userID , String searchStr) {
        Integer intPageNumber = Integer.parseInt(pageNumber);
        Integer intPpageSize = Integer.parseInt(pageSize);
        //查询与检索条件相符的普通员工自己的兑换记录总数
        int total = (int)findNumberOfExchangeByUserIDAndSearchStr(userID,searchStr);
        Map<String,Object> map;
        if (0<total){
            //拥有记录的情况
            //分页
            PageHelper.startPage(intPageNumber,intPpageSize);
            List<Exchange> exchangeList = findExchangeByUserIDAndSearchStr(userID,searchStr);
            //封装map
            Map<String,Object> data = new HashMap<>();
            //重组数据
            List<Map> exchangeMapList = reconstituteUsers(exchangeList);
            //员工的总数
            data.put("total",total);
            //员工的信息
            data.put("User",exchangeMapList);
            map = MapUtil.setMap2("1","成功",data);
        } else{
            map = MapUtil.setMap2("0","未找到相应兑换记录",null);
        }
        return map;
    }

    @Override
    public Exchange findExchangeById(String exchangeId) {
        Integer exchangeIdInt = Integer.parseInt(exchangeId);
        return exchangeMapper.selectByPrimaryKey(exchangeIdInt);
    }

    @Override
    public Map<String, Object> confirmReceive(String exchangeId, String userId) {

        //查询对应的兑换数据
        Exchange exchange = findExchangeById(exchangeId);
        Integer userIdInt = Integer.parseInt(userId);
        //声明所返回的map
        Map<String,Object> map;
        if (null!=exchange.getUserId()&&exchange.getUserId().equals(userIdInt)){
            //修改状态为3：用户已经确认领取完奖品了
            exchange.setExchangeStatus(3);
            try {
                exchangeMapper.updateByPrimaryKey(exchange);
            }catch (Exception e){
                e.printStackTrace();
                map = MapUtil.setMap2("1","状态更新失败",null);
            }
            map = MapUtil.setMap2("0","状态更新成功",null);


        }else {
            map = MapUtil.setMap2("2","未找到对应数据或者不是当前账号操作",null);
        }
        return map;
    }

    @Override
    public List<Exchange> findExchangeByUserIDAndPrizeID(int userID, int prizeId) {
        ExchangeExample example = new ExchangeExample();
        ExchangeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        criteria.andPrizeIdEqualTo(prizeId);
        List<Exchange> exchangeList = exchangeMapper.selectByExample(example);
        return exchangeList;
    }

    @Override
    public int insert(Exchange exchange) {
        return exchangeMapper.insert(exchange);
    }
}
