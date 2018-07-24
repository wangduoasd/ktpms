package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.PositionMapper;
import com.kaituo.pms.domain.DeptPosition;
import com.kaituo.pms.domain.Position;
import com.kaituo.pms.domain.PositionExample;
import com.kaituo.pms.service.DeptPositionService;
import com.kaituo.pms.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ktpms
 * @description: 职位相关的Service
 * @author: su
 * @create: 2018-07-24 18:23
 **/
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper positionMapper;

    @Autowired
    DeptPositionService deptPositionService;

    /** 
    * @Description:
    * @Param:
    * @return:  
    * @Author: su
    * @Date: 2018/7/24 
    */ 
    @Override
    public Position findPositionNameById(DeptPosition deptPosition) {
        PositionExample positionExample = new PositionExample();
        PositionExample.Criteria criteria = positionExample.createCriteria();
        int positionId = deptPosition.getPositionId();
        criteria.andPositionIdEqualTo(positionId);

        return positionMapper.selectByPrimaryKey(positionId);
    }

    @Override
    public List<Position> findPosition(String deptId) {
        List<Position> positionList = new ArrayList<>();
        List<DeptPosition> deptPositionList = deptPositionService.findPositionIdByDeptId(deptId);

        if (null!=deptPositionList&&deptPositionList.size()>0){

            for (DeptPosition deptPosition: deptPositionList) {

                Position position =findPositionNameById(deptPosition);
                positionList.add(position);
            }
        }else {
            return null;
        }
        return positionList;
    }
}
