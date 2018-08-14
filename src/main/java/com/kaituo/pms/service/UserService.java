package com.kaituo.pms.service;


import com.kaituo.pms.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
    * 从用户视图中获取除超级管理员外全部数据
    * @Param:
    * @return:
    * @Author: 苏泽华
    * @Date: 2018/8/8
    */
    List<User> listUserRankingByPage();

    /**
     * 通过ID查询个人信息
     * @Description: 个人中心我的信息
     * @Param:  id
     * @return:
     * @Author: 侯鹏
     * @Date:2018/8/8
     */
    User findPersonalDetail(int userid);

    /**
     * 查询所有名字或部门有关键字的员工数量
     * @Description 查询所有名字或部门有关键字的员工数量
     * @Param condition
     * @return long
     * @Author 苏泽华
     * @Date 2018/8/8
     */
    long countUsersByView();

    /**
     * 从用户视图中获取除超级管理员外条件相符合的全部数据
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    List<User> selectUsersByViewAndCondition(String condition);

    /**
     * 从用户视图中获取除超级管理员外条件相符合的全部数据的条数
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    long countUsersByViewAndCondition(String condition);
}
