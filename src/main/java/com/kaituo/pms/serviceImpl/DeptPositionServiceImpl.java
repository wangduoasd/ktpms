package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.DeptPositionMapper;
import com.kaituo.pms.domain.DeptPosition;
import com.kaituo.pms.domain.DeptPositionExample;
import com.kaituo.pms.service.DeptPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: ktpms
 * @description:
 * @author: su
 * @create: 2018-07-24 18:51
 **/
@Service
public class DeptPositionServiceImpl implements DeptPositionService {

    @Autowired
    DeptPositionMapper deptPositionMapper;

    @Override
    public List<DeptPosition> findPositionIdByDeptId(String deptId) {
        Integer IdeptId = Integer.parseInt(deptId);
        DeptPositionExample deptPositionExample = new DeptPositionExample();
        DeptPositionExample.Criteria criteria = deptPositionExample.createCriteria();
        criteria.andDeptIdEqualTo(IdeptId);
        return deptPositionMapper.selectByExample(deptPositionExample);
    }
}
