package com.kaituo.pms.service;

import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.bean.Integral;

import java.util.List;
import java.util.Map;

/**
 * 查询积分
* @Description: 个人中心，积分明细
* @Param:  
* @return:  
* @Author: 侯鹏
* @Date:  
*/ 
public interface IntegralService {
//    List<Integral> listIntegeral(int uid);

    long integeralTotal(int uid);

    List<Map<String , Object>> listIntegeral(int uid);

    int addPrizeIntegral(int changint,int userId,String changestr,int endnum);
}
