package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.domain.Integral;
import com.kaituo.pms.domain.IntegralExample;
import com.kaituo.pms.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
