package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.domain.ExchangeExample;
import com.kaituo.pms.domain.Integral;
import com.kaituo.pms.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @program: pms
 * @description: 有关积分的
 * @author: su
 * @create: 2018-07-26 16:45
 **/
@Service
public class InterfaceServiceImpl implements IntegralService {
    @Autowired
    IntegralMapper integralMapper;
    @Override
    public int addNewUserIntegral(Integer user_id, int changeInt , String operator) {
//        Integral integral = new Integral();
//        integral.setIntegralId(null);
//        integral.setIntegralTotal(0);
//        integral.setUserId(user_id);
//        integral.setIntegralChangestr("初始积分");
//        integral.setIntegralTime(new Date());
//        integral.setIntegralStatus(1);
//        integral.setIntegralChangeint(changeInt);
//        integral.setIntegralOperator(operator);
//        Integer integer = integralMapper.insert2(null,0,user_id,"初始积分",integral.getIntegralTime(),
//        1,changeInt,operator);
//        return integer;
        return 0;
    }
}
