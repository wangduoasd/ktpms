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
        Dept dept1 = findDeptByName(dept.getDeptName());
        if(dept1!=null)
            return 2;
        deptMapper.insertSelective(dept);
        int deptId = findDeptByName(dept.getDeptName()).getDeptId();
       return positionService.addPositons(positionArray,deptId);

    }
    @Override
    public Dept findDeptByName(String deptName)  {
      DeptExample deptExample = new DeptExample();
        deptExample.createCriteria().andDeptNameEqualTo(deptName);
        List<Dept> depts = deptMapper.selectByExample(deptExample);
        return depts.get(0);

    }

    @Override
    public int delDept(int deptId)  {
        int i = deptMapper.checkDept(deptId);
        if(i==0){
            return deptMapper.deleteByPrimaryKey(deptId);
        }
        return i+1;
    }

    @Override
    public int upDept(Dept dept, String[] positionArray) {

        deptMapper.updateByPrimaryKeySelective(dept);
       return positionService.addPositons(positionArray,dept.getDeptId());

    }


}
