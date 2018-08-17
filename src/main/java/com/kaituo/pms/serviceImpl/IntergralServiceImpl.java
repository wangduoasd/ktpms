package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Integral;
import com.kaituo.pms.bean.IntegralExample;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.bean.UserExample;
import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 查询个人积分明细
* @Description:个人中心，积分明细
* @Param:  
* @return: list
* @Author: 侯鹏
* @Date: 2018/8/10
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntergralServiceImpl implements IntegralService {
    @Autowired
    IntegralMapper integralMapper;
    @Autowired
    UserMapper userMapper;

//    @Override
////    public List<Integral> listIntegeral(int uid) {
////        List<Integral> listIntergral  = integralMapper.selectALLIntegral();
////        return listIntergral;
////    }


    @Override
    public long integeralTotal(int uid) {
        IntegralExample integralExample = new IntegralExample();
        IntegralExample.Criteria integralCriteria = integralExample.createCriteria();
        integralCriteria.andUserIdEqualTo(uid);
        return integralMapper.countByExample(integralExample);
    }

    @Override
    public List<Map<String, Object>> listIntegeral(int uid) {
       /* IntegralExample integralExample = new IntegralExample();
        IntegralExample.Criteria integralCriteria = integralExample.createCriteria();
        integralCriteria.andUserIdEqualTo(uid);*/

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
                    map.put("integralOperator" , "");
                    map.put("integralTime" , integral.getIntegralTime());
                    map.put("integralEndnum" , integral.getIntegralEndnum());
                    integralMap.add(map);
                }

                //
            }
        }
        return integralMap;
    }
}