package com.kaituo.pms.service;

import com.kaituo.pms.domain.Integral;

import java.util.Map;

/**
 * @program: pms
 * @description: 关于积分的
 * @author: su
 * @create: 2018-07-26 16:22
 **/

public interface IntegralService {
    /**
    * @Description: 添加员工时专用的添加积分记录
    * @Param:
    * @return:
    * @Author: su
    * @Date: 2018/7/26
    */
    int addNewUserIntegral(Integer user_id , int changeInt , String operator);
    /** 
    * @Description: 个人中心查询积分
    * @Param:  int pageNumber , int pageSize , int userID
    * @return:  
    * @Author: su
    * @Date: 2018/7/29 
    */
    Map<String , Object> findIntegralDetail(int pageNumber , int pageSize , int userID);

    /** 
    * @Description: 其他service用integralMapper的insert方法
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/29 
    */
    int insert(Integral integral);
}
