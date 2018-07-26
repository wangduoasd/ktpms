package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.domain.User;
import com.kaituo.pms.domain.UserExample;
import com.kaituo.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: ktpms
 * @description: 关于用户的service
 * @author: su
 * @create: 2018-07-25 13:52
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public long findNumberOfUser() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        return userMapper.countByExample(userExample);
    }

    @Override
    public List<User> findUsers() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("user_integral desc");
        UserExample.Criteria criteria = userExample.createCriteria();
        return userMapper.selectByExample(userExample);

    }

    @Override
    public long findNumberOfUserByCondition(String condition) {
        return 0;
    }

    @Override
    public List<User> findUserByCondition(String condition) {
        return null;
    }
}
