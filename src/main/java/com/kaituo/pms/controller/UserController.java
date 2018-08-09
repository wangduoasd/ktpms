package com.kaituo.pms.controller;


import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.util.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        public Msg findPersonalDetail(@PathVariable("id") int id) {
                try {
                        User user = userService.findPersonalDetail(id);
                        return Msg.success().add("user", user);
                } catch (Exception e) {
                        log.error("" + e.getMessage());
                        return Msg.fail();
                }

        }


}