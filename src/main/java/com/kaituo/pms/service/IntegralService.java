package com.kaituo.pms.service;

import com.kaituo.pms.domain.Integral;

import java.util.List;

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
    int upUserIntegral(Integral integral);

    List<Integral> findIntegralDetail(Integer userid);
}
