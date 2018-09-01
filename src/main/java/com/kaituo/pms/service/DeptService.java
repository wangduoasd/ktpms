package com.kaituo.pms.service;

import com.kaituo.pms.bean.Dept;

import java.util.List;

public interface DeptService {
    Dept findDeptNameById (int deptId);
    List<Dept> getAllDeptName();


    //部门设置

    List<Dept> findAllDept();
   //添加部门
    int addDept(Dept dept,List<String>  positionArray);

    List<Dept> findDeptByName(String deptName);

    //删除部门，删除之前先检查
    int delDept(int deptId) ;
    //修改部门
    int upDept(Dept dept, List<String>  positionArray);

    Dept getDeptById(int deptId);
}
