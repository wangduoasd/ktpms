package com.kaituo.pms.serviceImpl;


import com.kaituo.pms.bean.UserExample;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.service.UserService;

import com.kaituo.pms.vo.LeaderboardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.kaituo.pms.bean.User;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
/**
 * @program: ktpms
 * @description: UserService的实现
 * @author: 由苏泽华创建
 * @create: 2018-08-08 14:35
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
    * @Description:  从用户视图中获取除超级管理员外全部数据
    * @Param:
    * @return:
    * @Author: 苏泽华
    * @Date: 2018/8/8
    */
    @Override
    @Transactional
    public List<User> listUserRankingByPage() {
        List<User> userList = userMapper.selectUsersByView();
        return userList;
    }
/** 
* @Description: 个人中心我的信息
* @Param:  
* @return:  
* @Author: 侯鹏
* @Date:  2018/8/10
*/ 
    @Override
    @Transactional
    public User findPersonalDetail(int userid) {
        return userMapper.findPersonalDetail(userid);

    }

    /**
     * 查询所有名字或部门有关键字的员工数量
     * @Description 查询所有名字或部门有关键字的员工数量
     * @Param condition
     * @return long
     * @Author 苏泽华
     * @Date 2018/8/8
     */
    @Override
    @Transactional
    public long countUsersByView() {
        return userMapper.countUsersByView();
    }

    /**
     * 从用户视图中获取除超级管理员外条件相符合的全部数据
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @Override
    @Transactional
    public List<User> selectUsersByViewAndCondition(String condition) {
        List<User> userList = userMapper.selectUsersByViewAndCondition(condition);
        return userList;
    }

    /**
     * 从用户视图中获取除超级管理员外条件相符合的全部数据的条数
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @Override
    @Transactional
    public long countUsersByViewAndCondition(String condition) {
        return userMapper.countUsersByViewAndCondition(condition);
    }
}
