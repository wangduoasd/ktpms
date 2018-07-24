package com.kaituo.pms.service;

import com.kaituo.pms.domain.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
    * @Param:
    * @return:  
    * @Author: su
    * @Date: 2018/7/24 
    */ 
    List<Dept> findAll();
}
