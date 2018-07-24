package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.DeptMapper;
import com.kaituo.pms.domain.Dept;
import com.kaituo.pms.domain.DeptExample;
import com.kaituo.pms.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;
    @Override
    @Autowired
    public List<Dept> findAll() {
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
//        criteria.andDeptIdEqualTo(2);
        return deptMapper.selectByExample(deptExample);
//        Dept dept = deptMapper.selectByPrimaryKey(3);
//        List<Dept>list = new ArrayList<>();
//        list.add(dept);
//        return  list;
    }
}
