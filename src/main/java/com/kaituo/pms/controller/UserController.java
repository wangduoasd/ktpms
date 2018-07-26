package com.kaituo.pms.controller;


import com.github.pagehelper.PageHelper;
import com.kaituo.pms.domain.Dept;
import com.kaituo.pms.domain.User;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    UserService userService;

    @Autowired
    DeptService deptService;

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

    @PostMapping("findAllUserByPage")
    public Map<String,Object> findAllUserByPage(String pageNumber, String pageSize){
        Integer intPageNumber = Integer.parseInt(pageNumber);
        Integer intPpageSize = Integer.parseInt(pageSize);

        //查询所有员工总数
        int total = (int) userService.findNumberOfUser();
        //查询所有待领取任务的信息
        //分页
        PageHelper.startPage(intPageNumber,intPpageSize);
        List<User> list = userService.findUsers();
        //修改用户数据，将部门ID变为部门名称
        List<Map> userMapList = new ArrayList<>();
        for(User user : list){
            Map<String , Object> userMap = new HashMap<>(8);
            //从旧数据中得到部门id
            int DeptId= user.getDeptId();
            //通过部门ID得到部门对象
            Dept dept = deptService.findDeptNameByDeptID(DeptId);
            //封装为新的数据
            userMap.put("userId",user.getUserId());
            userMap.put("userName",user.getUserName());
            //部门名字
            userMap.put("deptName",dept.getDeptName());
            userMap.put("userPosition",user.getUserPosition());
            userMap.put("userUsername",user.getUserUsername());
            userMap.put("userInductiontime",user.getUserInductiontime());
            userMap.put("userIntegral",user.getUserIntegral());
            userMap.put("userStatus",user.getUserStatus());
            //添加到新的集合中
            userMapList.add(userMap);
        }
        //封装map
        Map<String,Object> data = new HashMap<>(2);

        //员工的总数
        data.put("total",total);
        //员工的信息
        data.put("Task",userMapList);
        Map<String,Object> map = MapUtil.setMap2("1","成功",data);
        return map;
    }

}
