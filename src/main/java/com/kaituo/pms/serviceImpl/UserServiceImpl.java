package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.User;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findPersonalDetail(int userid) {
        return userMapper.findPersonalDetail(userid);

    }
}
