package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Position;
import com.kaituo.pms.bean.PositionExample;
import com.kaituo.pms.dao.PositionMapper;
import com.kaituo.pms.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: PositionServiceImpl
 * @ProjectName pms
 * @Description:
 * @date 2018/8/21 002115:53
 */
@Service
@Transactional
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Override
    public List<Position> getPositionNameBydeptId(int deptId) {
        PositionExample positionExample = new PositionExample();
        positionExample.createCriteria().andDeptIdEqualTo(deptId);
        return positionMapper.selectByExample(positionExample);
    }
    @Override

    public int addPositons(List<String>  positionArray,int deptId) {
        PositionExample positionExample = new PositionExample();
        positionExample.createCriteria().andDeptIdEqualTo(deptId);
        positionMapper.deleteByExample(positionExample);
        Position position = new Position();
        for (String p:positionArray ) {
            position.setPositionName(p);
            position.setDeptId(deptId);
            positionMapper.insertSelective(position);
        }
        return 1;
    }
}
