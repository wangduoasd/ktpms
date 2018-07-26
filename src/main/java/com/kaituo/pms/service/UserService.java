package com.kaituo.pms.service;

import com.kaituo.pms.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 查询所有员工数量
    * @Param:
    * @return:
    * @Author: su
    * @Date: 2018/7/24
    */
    long findNumberOfUser();

    /** 
    * 查询所有员工信息
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/25 
    */
    List<User> findUsers();
    /**
     * 查询所有名字或部门有关键字的员工数量
    * @Description 查询所有名字或部门有关键字的员工数量
    * @Param condition
    * @return long
    * @Author su
    * @Date 2018/7/25
    */ 
    long findNumberOfUserByCondition(String condition);


    /**
     * 查询所有名字或部门有关键字的员工
     * @param condition
     * @return List<User>
     */
    List<User> findUserByCondition(String condition);

}
