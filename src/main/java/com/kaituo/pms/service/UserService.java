package com.kaituo.pms.service;

import com.kaituo.pms.bean.User;

import java.util.Map;

public interface UserService {
/**
* @Description: 个人中心我的信息
* @Param:  id
* @return:
* @Author: 侯鹏
* @Date:2018/8/8
*/
    User findPersonalDetail(int userid);
}
