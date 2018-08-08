package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Dept;
import com.kaituo.pms.bean.DeptExample;
import com.kaituo.pms.dao.DeptMapper;
import com.kaituo.pms.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;
    @Override
    public Dept findDeptNameById(int deptId) {
       DeptExample deptExample = new DeptExample();
       DeptExample.Criteria criteria = deptExample.createCriteria();
       return deptMapper.selectByPrimaryKey(deptId);
    }
}
