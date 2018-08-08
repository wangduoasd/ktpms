package com.kaituo.pms.service;


import com.kaituo.pms.vo.LeaderboardVO;

import java.util.List;

public interface UserService {

    /** 
    * 从用户视图中获取除超级管理员外全部数据
    * @Param:  
    * @return:  
    * @Author: 苏泽华
    * @Date: 2018/8/8 
    */ 
    List<LeaderboardVO> listUserRankingByPage();
}
