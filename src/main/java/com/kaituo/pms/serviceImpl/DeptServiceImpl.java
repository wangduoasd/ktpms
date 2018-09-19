package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Dept;
import com.kaituo.pms.bean.DeptExample;
import com.kaituo.pms.dao.DeptMapper;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int addDept(Dept dept,List<String>  positionArray) {
        List<Dept> depts=findDeptByName(dept.getDeptName());
        if(depts.size()!=0){
            return 2;
        }
        deptMapper.insertSelective(dept);
        int deptId =findDeptByName(dept.getDeptName()).get(0).getDeptId();

        return positionService.addPositons(positionArray,deptId);

    }
    @Override
    public List<Dept> findDeptByName(String deptName){
      DeptExample deptExample = new DeptExample();
        deptExample.createCriteria().andDeptNameEqualTo(deptName);
        List<Dept> depts = deptMapper.selectByExample(deptExample);
        return depts;
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
    public int upDept(Dept dept, List<String>  positionArray) {
        List<Dept> depts=findDeptByName(dept.getDeptName());
        if(depts==null||depts.size()==0||depts.get(0).getDeptId().equals(dept.getDeptId())){
            deptMapper.updateByPrimaryKeySelective(dept);
            List<String> strings = positionService.checkPositions(positionArray, dept.getDeptId());
            if(strings==null){
                return 3;
            }
            return positionService.addPositons(strings,dept.getDeptId());
        }

            return 2;



    }

    @Override
    public Dept getDeptById(int deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }
}
