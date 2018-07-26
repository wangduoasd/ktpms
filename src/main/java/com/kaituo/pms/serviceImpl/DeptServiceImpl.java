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
    public List<Dept> findAll() {
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        return deptMapper.selectByExample(deptExample);
    }

    @Override
    public Dept findDeptNameByDeptID(int deptID) {
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        return deptMapper.selectByPrimaryKey(deptID);
    }
}
