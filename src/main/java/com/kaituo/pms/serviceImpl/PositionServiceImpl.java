package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.PositionMapper;
import com.kaituo.pms.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张金行
 * @version 1.0
 * @Title: PositionServiceImpl
 * @ProjectName pms
 * @Description:
 * @date 2018/8/21 002115:53
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Override
    public String[] getAllPositionName() {
        return positionMapper.getAllPositionName();
    }
}
