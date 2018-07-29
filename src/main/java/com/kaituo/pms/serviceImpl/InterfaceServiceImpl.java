package com.kaituo.pms.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.domain.ExchangeExample;
import com.kaituo.pms.domain.Integral;
import com.kaituo.pms.domain.IntegralExample;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
        Integral integral = new Integral();
        integral.setIntegralId(null);
        integral.setIntegralTotal(changeInt);
        integral.setUserId(user_id);
        integral.setIntegralChangestr("初始积分");
        integral.setIntegralTime(new Date());
        integral.setIntegralStatus(1);
        integral.setIntegralChangeint(changeInt);
        integral.setIntegralOperator(operator);
//        Integer integer = integralMapper.insert(integral);
        Integer integer = integralMapper.insert2(null,changeInt,user_id,"初始积分",integral.getIntegralTime(),
        1,changeInt,operator);
        return integer;
//        return 0;
    }

    @Override
    public Map<String, Object> findIntegralDetail(int pageNumber , int pageSize , int userID) {
        IntegralExample integralExample = new IntegralExample();
        IntegralExample.Criteria criteria = integralExample.createCriteria();
        criteria.andUserIdEqualTo(userID);
        PageHelper.startPage(pageNumber,pageSize);
        List<Integral> list = integralMapper.selectByExample(integralExample);
        if(null!=list&&list.size()>0){
            return MapUtil.setMap2("0","成功",list);
        }else {
            return MapUtil.setMap2("1", "没有找到记录", null);
        }
    }

    @Override
    public int insert(Integral integral) {
        return integralMapper.insert(integral);
    }
}
