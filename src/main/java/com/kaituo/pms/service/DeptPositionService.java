package com.kaituo.pms.service;

import com.kaituo.pms.domain.Dept;
import com.kaituo.pms.domain.DeptPosition;

import java.util.List;

public interface DeptPositionService {

    /**
     *通过部门id检索对应的职位
    * @Description:
    * @Param: Dept dept 部门实体
    * @return:  List<DeptPosition>
    * @Author: su
    * @Date: 2018/7/24
    */
    List<DeptPosition> findPositionIdByDeptId(String deptId);
}
