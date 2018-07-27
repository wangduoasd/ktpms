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

    /**
     * 根据部门id查部门
     * @param deptID
     * @return Dept
     * @Author: su
     * @Date: 2018/7/25
     */
    Dept findDeptNameByDeptID(int deptID);

    /**
     *添加部门
     * @param dept
     * @Author: 张金行
     * @Date: 2018/7/26
     */
    public void addDept(Dept dept) ;
    /**
     *添加部门
     * @param dept
     * @Author: 张金行
     * @Date: 2018/7/26
     */
    public void updateDeptById(Dept dept) ;

    public void deleteDeptById(Integer deptId);
}
