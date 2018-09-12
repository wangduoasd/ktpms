package com.kaituo.pms.service;


import com.kaituo.pms.bean.Login;
import com.kaituo.pms.bean.User;

import java.util.List;

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
     * 通过员工id获取对应的一个员工数据
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

    /**
     * 通过员工id获取对应的一个员工数据
     * @Description: 个人中心我的信息
     * @param userid 员工id
     * @return:
     * @Author: 苏泽华
     * @Date:2018/8/9
     */
    User getUserFromTable(int userid);
    /**
     * @Description: 综服中心-员工设置-分页查询所有员工
     * @param
     * @return
     * @throws
     * @author 张金行
     * @date 2018/8/17 0017 17:35
     */
    List<User>findAllUser();
    User getUserById(int userId);
    List<User> findByKeyWord(String keyWord);

    int addUser(User user);

    int upUser(User user,int userId);

    List<User> findUserRole();

    int upUserIntegral(User user);

    List<User> findRoleUser();

    User findUserById(int userId);

    int upUserPassword(int userId,String oldPassWord, String newPassWord);

    int upUserIntegral(int operatorId,int userId,String changeStr,int changeInt);

     Login login(int userId, String password);

     int findUserByDeptPositionId(int deptPositionId);
}
