package com.kaituo.pms.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.domain.Integral;
import com.kaituo.pms.domain.IntegralExample;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InregralServiceImpl implements IntegralService {
    @Autowired
    IntegralMapper integralMapper;
    @Override
    public int upUserIntegral(Integral integral) {
        return integralMapper.insertSelective(integral);
    }

    @Override
    public List<Integral> findIntegralDetail(Integer userid) {
        IntegralExample integralExample=new IntegralExample();
        IntegralExample.Criteria criteria = integralExample.createCriteria();
        return integralMapper.selectByExample(integralExample);
    }

    @Override
    public int addNewUserIntegral(Integer user_id, int changeInt, String operator) {
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
