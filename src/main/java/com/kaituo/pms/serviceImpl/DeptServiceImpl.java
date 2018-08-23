package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Dept;
import com.kaituo.pms.bean.DeptExample;
import com.kaituo.pms.dao.DeptMapper;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.service.PositionService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;
    @Autowired
    PositionService positionService;
    @Override
    public Dept findDeptNameById(int deptId) {
       return deptMapper.selectByPrimaryKey(deptId);
    }
    @Override
  public List<Dept> getAllDeptName(){
        return deptMapper.getAllDeptName();
  }

    @Override
    public List<Dept> findAllDept() {
        DeptExample deptExample = new DeptExample();
        return deptMapper.selectByExample(deptExample);

    }
    @Override
    public int addDept(Dept dept,String[] positionArray) {
        deptMapper.insertSelective(dept);
        int deptId = findDeptIdByName(dept.getDeptName());
        positionService.addPositons(positionArray,deptId);
        return 1;
    }
    @Override
    public int findDeptIdByName(String deptName)  {
      DeptExample deptExample = new DeptExample();
        deptExample.createCriteria().andDeptNameEqualTo(deptName);
        List<Dept> depts = deptMapper.selectByExample(deptExample);
        return depts.get(0).getDeptId();

    }

    @Override
    public int delDept(int deptId)  {
       return deptMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public int upDept(Dept dept, String[] positionArray) {
        deptMapper.updateByPrimaryKeySelective(dept);
        positionService.addPositons(positionArray,dept.getDeptId());
        return 1;
    }
}
