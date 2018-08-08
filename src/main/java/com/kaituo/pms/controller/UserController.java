package com.kaituo.pms.controller;


import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.util.MapUtils;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
        @Autowired
        UserService userService;
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