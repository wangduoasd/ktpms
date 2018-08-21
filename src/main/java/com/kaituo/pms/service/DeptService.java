package com.kaituo.pms.service;

import com.kaituo.pms.bean.Dept;

import java.util.List;

public interface DeptService {
    Dept findDeptNameById (int deptId);
    List<Dept> getAllDeptName();

    List<Dept> findAllDept();
}
