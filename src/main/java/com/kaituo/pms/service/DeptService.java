package com.kaituo.pms.service;

import com.kaituo.pms.bean.Dept;

import java.sql.SQLException;
import java.util.List;

public interface DeptService {
    Dept findDeptNameById (int deptId);
    List<Dept> getAllDeptName();


    //部门设置

    List<Dept> findAllDept();
   //添加部门
    int addDept(Dept dept,String[] positionArray);

    Dept findDeptByName(String deptName);

    //删除部门，删除之前先检查
    int delDept(int deptId) ;
    //修改部门
    int upDept(Dept dept, String[] positionArray);

}
