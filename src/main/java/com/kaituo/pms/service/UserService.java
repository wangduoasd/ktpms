package com.kaituo.pms.service;

import com.kaituo.pms.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

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

    /**
    * 重新组成user并封装为Map集合
    * @Param:  List<User>
    * @return:  List<Map>
    * @Author: su
    * @Date: 2018/7/26
    */
    List<Map> reconstituteUsers(List<User> list);

    /**
    * @Description:  账号唯一性校验
    * @Param:  账号
    * @return:  boolean,false数据库中没有这个账号。true为数据库中有这个账号
    * @Author: su
    * @Date: 2018/7/26
    */
    boolean findUserUserName(String condition);

    /**
    * @Description: 修改员工信息
    * @Param: 用户账号，姓名，部门id，职位名称，入职时间，状态
    * @return:  true：成功。false：失败
    * @Author: su
    * @Date: 2018/7/26
    */
    boolean updateUser(String userUserName ,String userName , String deptID , String position ,
                             String userPassword , String inductionTime , String userStatus);
    /**
     * @Description: 积分中心—积分排行榜-分页查询
     * @Param:
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/28
     */
    List<User> findRankingByPage();
    /**
     * @Description: 积分中心—积分排行榜-搜索按钮
     * @Param:
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/28
     */
    List<User>searchRanking(String keyword);
    /**
     * @Description: 个人中心-我的信息
     * @Param:pn页数
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/26
     */
    User findPersonalDetail(Integer userid);
    /**
     * @Description: 登录
     * @Param:
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/29
     */
    User login(User user);
  /*  User findByUserName(String username);*/
}
