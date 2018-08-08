package com.kaituo.pms.controller;

import com.kaituo.pms.util.MapUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.util.CodeAndMessageEnum;
import com.kaituo.pms.util.MapUtil;
import com.kaituo.pms.vo.LeaderboardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * @program: ktpms
 * @description: 有关用户的controller
 * @author: 由苏泽华创建
 * @create: 2018-08-08 13:54
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "userIntegrals/{pageNumber}/{pageSize}")
    public Map<String , Object> findRankingByPage(@PathVariable(value = "pageNumber") @RequestParam(defaultValue = "1")
                                                              int pageNumber,
                                                  @PathVariable(value = "pageSize") @RequestParam(defaultValue = "8")
                                                          int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        //从用户视图中获取除超级管理员外全部数据
        try {
            List<LeaderboardVO> leaderboardList = userService.listUserRankingByPage();
            //未获得员工信息的情况下
            if (null==leaderboardList||leaderboardList.size()<=0){
                return MapUtil.setMap(CodeAndMessageEnum.USER_FIND_RANKING_BY_PAGE_NULL,null);
            }
            PageInfo pageInfo = new PageInfo(leaderboardList);
            //成功
            return MapUtil.setMap(CodeAndMessageEnum.USER_FIND_RANKING_BY_PAGE_SUCCESS , pageInfo);
        } catch (Exception e){
            e.printStackTrace();
            //发生异常
            return MapUtil.setMap(CodeAndMessageEnum.USER_FIND_RANKING_BY_PAGE_ERROR,null);
        }
    }

    /**
     * @Description: 个人中心我的信息
     * @Param: id
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/8
     */
    @GetMapping(value="users/{id}")
    public Map<String , Object> findPersonalDetail(@PathVariable("id") int id) {
/*                if (null!=user) {
           int userID = user.getUserId();
          return UserService.
       }else {
           return MapUtils.setMap2("1","获取员工信息失败","空值");
      }
        }*/

          /*      PageHelper.startPage(1,5);
                Map<String, Object> personalDetail = userService.findPersonalDetail(id);
                PageInfo pageInfo = new PageInfo(personalDetail,5);*/
        User personalDetail = userService.findPersonalDetail(id);
        return MapUtils.setMap2("1","获取员工信息失败",personalDetail);
    }
}
