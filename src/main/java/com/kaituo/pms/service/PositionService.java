package com.kaituo.pms.service;

import com.kaituo.pms.domain.DeptPosition;
import com.kaituo.pms.domain.Position;

import java.util.List;

public interface PositionService {
    /** 
    * 通过关联表中查到的关联数据得到相对应的职位id
     * 并通过职位id查询职位数据
    * @Param:  DeptPosition
    * @return:  Position
    * @Author: su
    * @Date: 2018/7/24 
    */ 
    Position findPositionNameById(DeptPosition deptPosition);

    /** 
    * 得到职位实体
    * @Param:  deptId
    * @return:  List<Position>
    * @Author: su
    * @Date: 2018/7/24 
    */ 
    List<Position> findPosition(String deptId);
}
