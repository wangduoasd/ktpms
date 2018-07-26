package com.kaituo.pms.controller;


import com.github.pagehelper.PageHelper;
import com.kaituo.pms.domain.Exchange;
import com.kaituo.pms.service.ExchangeService;
import com.kaituo.pms.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Description:
 *@Param:
 *@return:
 *@Author: 郭士伟
 *@Date: 2018/7/23
*/
@RestController
@RequestMapping(value = "exchange")
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;
    /** 
     *@Description: 综服中心-商品兑换列表-分页查询
     *@Param: 
     *@return: 
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    @ResponseBody
    @RequestMapping(value = "findChangeList",method = RequestMethod.POST)
    public Map<String,Object> findChangeList(@RequestParam int pageNumber, @RequestParam int pageSize){
        //状态为1的兑换列表为员工已经点击兑换，需要综服中心确认兑换的
        //状态为2的兑换列表为综服中心已经确认兑换，但是员工没有确认收到商品的（没有确认收货）
        //分页
        PageHelper.startPage(pageNumber,pageSize);
        //根据状态升序查询所有兑换列表,并把
        List<Object> exchangeArray = exchangeService.findExchangeByStatusRank();

        //查询exchange所有数量
        long total = exchangeService.findNumberOfExchange();
        //将分页信息封装到一个map里面
        Map<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("exchange",exchangeArray);
        Map<String,Object> map = MapUtil.setMap2("1","成功",data);
        return map;
    }

    /** 
     *@Description: 商品兑换列表_操作_确认兑换
     *@Param: 
     *@return: 
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    @ResponseBody
    @PostMapping(value = "confirmExchange")
    public Map<String,Object> confirmExchange(@RequestParam int exchangeId){
        //确认兑换就是把兑换列表里面的状态改为2，exchange_status=2表示宗服部已经确认兑换了（商品已发货）
        int status =2;
        int i = exchangeService.updateExchangeByID(exchangeId,status);
        if(i !=1){
            return MapUtil.setMap2("0","兑换失败",null);
        }
        return MapUtil.setMap2("1","兑换成功",null);
    }

    /** 
     *@Description: 综服中心-商品兑换列表_搜索按钮
     *@Param:String searchStr 搜索栏的字符串
     *@return:
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    @ResponseBody
    @PostMapping(value = "searchChangeList")
    public Map<String,Object> searchChangeList(@RequestParam String searchStr){
        List<Object> list =exchangeService.findExchangeBySearchStr(searchStr);
        if(list.size() > 0 && list != null){
            Map map = new HashMap();
            map.put("total",list.size());
            map.put("exchangeList",list);
            return MapUtil.setMap2("1","成功",list);
        }
       return MapUtil.setMap2("0","没有查询出结果",null);
    }
}
