package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.service.AllpertaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangduo
 * @date 2018/11/6 - 13:17
 */
public class AllpertaskServiceImpl implements AllpertaskService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void distribute_Allpertask(Allpertask allpertask) {

        List<Integer> userList=userMapper.findall ();
        for (Integer id:userList) {

        }
    }

    @Override
    public void delete_Allpertask(Integer Allpertask_id) {

    }
}
