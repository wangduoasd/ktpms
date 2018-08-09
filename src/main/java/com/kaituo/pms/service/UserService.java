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
     * @Description: 个人中心我的信息
     * @Param:  id
     * @return:
     * @Author: 侯鹏
     * @Date:2018/8/8
     */
    User findPersonalDetail(int userid);
}
