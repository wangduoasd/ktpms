package com.kaituo.pms.serviceImpl;


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
    public List<LeaderboardVO> listUserRankingByPage() {
        List<LeaderboardVO> userList = userMapper.selectUsersByView();
        return userList;
    @Override
    public User findPersonalDetail(int userid) {
        return userMapper.findPersonalDetail(userid);

    }
}
