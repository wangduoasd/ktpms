package com.kaituo.pms.service;

import com.kaituo.pms.bean.Dept;

import java.sql.SQLException;
import java.util.List;

public interface DeptService {
    Dept findDeptNameById (int deptId);
    List<Dept> getAllDeptName();


    //部门设置

    List<Dept> findAllDept();

    int addDept(Dept dept,String[] positionArray);

    int findDeptIdByName(String deptName);

    int delDept(int deptId) ;

    int upDept(Dept dept, String[] positionArray);
}
