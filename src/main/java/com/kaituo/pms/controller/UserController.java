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

    /**
    * @Description: 默认员工分页
    * @Param:
    * @return:
    * @Author: su
    * @Date: 2018/7/26
    */
    @PostMapping("findAllUserByPage")
    public Map<String,Object> findAllUserByPage(String pageNumber, String pageSize){
        Integer intPageNumber = Integer.parseInt(pageNumber);
        Integer intPpageSize = Integer.parseInt(pageSize);

        //查询所有员工总数
        int total = (int) userService.findNumberOfUser();
        Map<String,Object> map;
        if (0>total){
        //查询所有待领取任务的信息
        //分页
        PageHelper.startPage(intPageNumber,intPpageSize);
        List<User> list = userService.findUsers();

        //修改用户数据，将部门ID变为部门名称
        List<Map> userMapList = userService.reconstituteUsers(list);
        //封装map
        Map<String,Object> data = new HashMap<>(2);

        //员工的总数
        data.put("total",total);
        //员工的信息
        data.put("User",userMapList);
            map = MapUtil.setMap2("1","成功",data);
        }else {
            map = MapUtil.setMap2("2","为啥没员工呢？",null);
        }
        return map;
    }

    /**
    * @Description: 有条件的员工分页
    * @Param:
    * @return:
    * @Author: su
    * @Date: 2018/7/26
    */
    @PostMapping("searchUserByNameOrDept")
    public Map<String,Object> searchUserByNameOrDept(String pageNumber, String pageSize,String condition){
        Integer intPageNumber = Integer.parseInt(pageNumber);
        Integer intPpageSize = Integer.parseInt(pageSize);

        //查询条件满足的员工总数
        int total = (int) userService.findNumberOfUserByCondition(condition);
        Map<String,Object> map;
        if (0>total){
            //查询条件满足的员工的信息
            //分页
            PageHelper.startPage(intPageNumber,intPpageSize);
            List<User> list = userService.findUserByCondition(condition);
            //修改用户数据，将部门ID变为部门名称
            List<Map> userMapList = userService.reconstituteUsers(list);
            //封装map
            Map<String,Object> data = new HashMap<>(2);

            //员工的总数
            data.put("total",total);
            //员工的信息
            data.put("User",userMapList);
            map = MapUtil.setMap2("1","成功",data);
        }else {
            map = MapUtil.setMap2("2","未找到相应员工",null);
        }

        return map;
    }

    /**
    * @Description: 添加用户
    * @Param:
    * @return:
    * @Author: su
    * @Date: 2018/7/26
    */
//    public Map<String , Object> addUser(){
//
//        return map;
//    }

    @PostMapping("checkUserUserName")
    public Map<String , Object> checkUserUserName(String condition){
        Map<String,Object> map;
        if (!userService.findUserUserName(condition)){
            map = MapUtil.setMap2("0","可以使用",true);
            return map;
        }else{
            map = MapUtil.setMap2("1","账号重复，请重新输入",false);
            return map;
        }
    }
}
