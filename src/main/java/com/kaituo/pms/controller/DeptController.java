package com.kaituo.pms.controller;


import com.kaituo.pms.domain.Dept;
import com.kaituo.pms.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *@Description:
 *@Param:
 *@return:
 *@Author: 郭士伟
 *@Date: 2018/7/23
*/
@RestController
@RequestMapping(value = "dept")
public class DeptController {
    @Autowired
    DeptService deptService;
    /**
     *@Description: 测试的
     *@Param:
     *@return:
     *@Author: 郭士伟
     *@Date: 2018/7/23
    */
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public void findAll(){
        List<Dept> list = deptService.findAll();
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }



    }

}
