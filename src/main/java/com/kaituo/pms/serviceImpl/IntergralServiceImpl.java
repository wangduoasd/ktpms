package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.*;
import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 查询个人积分明细
* @Description:个人中心，积分明细
* @Param:  
* @return: list
* @Author: 侯鹏
* @Date: 2018/8/10
*/
import java.util.*;

@Service
@Transactional
public class IntergralServiceImpl implements IntegralService {
    @Autowired
    IntegralMapper integralMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @Override
    public long integeralTotal(int uid) {
        IntegralExample integralExample = new IntegralExample();
        IntegralExample.Criteria integralCriteria = integralExample.createCriteria();
        integralCriteria.andUserIdEqualTo(uid);
        return integralMapper.countByExample(integralExample);
    }

    @Override
    public List<Map<String, Object>> listIntegeral(int uid) {
        IntegralExample integralExample = new IntegralExample();
        IntegralExample.Criteria criteria = integralExample.createCriteria();
        criteria.andUserIdEqualTo(uid);
        List<Map<String, Object>> integralMap = new ArrayList<>();
        List<Integral> integralList = integralMapper.selectByExample(integralExample);
        if(null != integralList && integralList.size() > 0){
            for (Integral integral : integralList) {
                Map<String , Object> map = new HashMap<>();
                User user;
                if (null != integral.getIntegralOperator()) {
                    user = userMapper.selectByPrimaryKey(integral.getIntegralOperator());
                    map.put("integralChangeInt" , integral.getIntegralChangeint());
                    map.put("integralChangeStr" , integral.getIntegralChangestr());
                    map.put("integralOperator" , user.getUserName());
                    map.put("integralTime" , integral.getIntegralTime());
                    map.put("integralEndnum" , integral.getIntegralEndnum());
                    integralMap.add(map);
                    //integralMap.add(map);
                }else {
                    map.put("integralChangeInt" , integral.getIntegralChangeint());
                    map.put("integralChangeStr" , integral.getIntegralChangestr());
                    map.put("integralTime" , integral.getIntegralTime());
                    map.put("integralEndnum" , integral.getIntegralEndnum());
                    integralMap.add(map);
                }
            }
        }
        return integralMap;
    }

    @Override
    public int addPrizeIntegral(int changint,int userId,String changestr,int endnum) {
        User user = userService.findUserById(userId);
        Integral integral = new Integral();
        integral.setUserId(userId);
        integral.setIntegralStartnum(user.getUserIntegral());
        integral.setIntegralTime(new Date());
        integral.setIntegralChangeint(changint);
        integral.setIntegralChangestr(changestr);
        integral.setIntegralEndnum(endnum);


        return integralMapper.insertSelective(integral);
    }



    @Override
    public int addIntegral(int operatorId,String changestr,int userId,int changeInt,int endNum ) {
        Integral integral = new Integral();
        integral.setIntegralOperator(operatorId);
        integral.setIntegralChangeint(changeInt);
        integral.setIntegralEndnum(endNum);
        integral.setIntegralTime(new Date());
        integral.setUserId(userId);
        integral.setIntegralChangestr(changestr);
        return integralMapper.insertSelective(integral);
    }
}
