package com.kaituo.pms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.UserService;

import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "userIntegrals/{pageNumber}/{pageSize}")
    public OutJSON findRankingByPage(@PathVariable(value = "pageNumber") @RequestParam(defaultValue = "1")
                                                              int pageNumber,
                                     @PathVariable(value = "pageSize") @RequestParam(defaultValue = "8")
                                                          int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        //从用户视图中获取除超级管理员外全部数据
        try {
            List<User> leaderboardList = userService.listUserRankingByPage();
            //未获得员工信息的情况下
            if (null==leaderboardList||leaderboardList.size()<=0){
                return  OutJSON.getInstance(CodeAndMessageEnum.USER_FIND_RANKING_BY_PAGE_NULL);
            }
            PageInfo pageInfo = new PageInfo(leaderboardList);
            //成功
            return OutJSON.getInstance(CodeAndMessageEnum.USER_FIND_RANKING_BY_PAGE_SUCCESS ,pageInfo);
        } catch (Exception e){
            e.printStackTrace();
            //发生异常
            return OutJSON.getInstance(CodeAndMessageEnum.USER_FIND_RANKING_BY_PAGE_ERROR);
        }
    }

    /**
     * @Description: 个人中心我的信息
     * @Param: id
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/8
     */
    /*@GetMapping(value="users/{id}")
    public OutJSON findPersonalDetail(@PathVariable("id") int id) {

                User personalDetail = userService.findPersonalDetail(id);
                return MapUtils.setMap2("1","获取员工信息失败",personalDetail);
        }*/

}