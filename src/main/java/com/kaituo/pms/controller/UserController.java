package com.kaituo.pms.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *@Description:
 *@Param:
 *@return:
 *@Author: 郭士伟
 *@Date: 2018/7/23
*/
@RestController
@RequestMapping(value = "user")
public class UserController {

    /**
     *@Description: 测试的
     *@Param:
     *@return:
     *@Author: 郭士伟
     *@Date: 2018/7/23
    */
    @RequestMapping(value = "hello",method = RequestMethod.POST)
    public void hello(){

        System.out.println("222");
    }


}
