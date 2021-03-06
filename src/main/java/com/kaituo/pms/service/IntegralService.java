package com.kaituo.pms.service;

import com.kaituo.pms.bean.ChangeIntegral;
import com.kaituo.pms.bean.Integral;
import com.kaituo.pms.bean.Prize;

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



    int addPrizeIntegral(int changint,int userId,String changestr,int endnum);
    int addIntegral(int operatorId,String changestr,int userId,int changeInt,int endNum );
    List<Integral>  selectIntegralById(int userId);

    /**
     * 根据用户ID修改对应的积分明细表
     */
    void updateByUserId(ChangeIntegral ci, Integer userId);
}
