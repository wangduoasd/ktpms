package com.kaituo.pms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.domain.Dept;
import com.kaituo.pms.domain.Task;
import com.kaituo.pms.domain.User;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.MapUtil;
import com.kaituo.pms.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
   /* *//**
     * @Description: 登录
     * @Param:
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/26
     *//*
    @PostMapping("login")
    public Msg login(User user1){
        User user = userService.login(user1);
        if(user!=null)
            return Msg.success().add("user",user);
        else
            return Msg.fail();
    }*/
    /**
    * @Description: 综服中心-员工设置 默认员工分页
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
        if (0<total){
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
    * @Description: 综服中心-员工设置 搜索按钮 模糊查询分页
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
        if (0<total){
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
            map = MapUtil.setMap2("0","未找到相应员工",null);
        }

        return map;
    }

    /**
    * @Description: 综服中心-员工设置-添加新员工按钮
    * @Param:
    * @return:
    * @Author: su
    * @Date: 2018/7/26
    */
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

    @PostMapping("updateUser")
    public Map<String , Object> updateUser(String userUserName ,String userName , String deptID , String position ,
                                                 String userPassword , String inductionTime , String userStatus){

        Map<String,Object> map;

        if (userService.updateUser(userUserName , userName , deptID , position , userPassword , inductionTime , userStatus)){
            map = MapUtil.setMap2("1","变更成功",true);
            return map;
        }
        map = MapUtil.setMap2("0","变更失败",false);
        return map;
    }
        /**
         * @Description: 积分中心-积分排行榜 分页查询
         * @Param:pn页数
         * @return:
         * @Author: 张金行
         * @Date: 2018/7/26
         */
        @PostMapping("findRankingByPage")
        public Msg findRankingByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
            PageHelper.startPage(pn, 5);
            List<User> list = userService.findRankingByPage();
            //封装map
            PageInfo pageInfo = new PageInfo(list, 5);
            return Msg.success().add("pageInfo", pageInfo);

        }
    /**
     * @Description: 积分中心-积分排行榜-搜索按钮  模糊查询分页
     * @Param:pn页数
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/26
     */
    @PostMapping("searchRanking")
    public Msg searchRanking(@RequestParam(value = "pn", defaultValue = "1") Integer pn,String keyword){
        PageHelper.startPage(pn, 13);
        List<User> list = userService.searchRanking(keyword);
        //封装map
        PageInfo pageInfo = new PageInfo(list, 5);
        return Msg.success().add("pageInfo", pageInfo);

    }
/*    *//**
     * @Description: 个人中心-我的信息
     * @Param: userid 用户ID
     * @return:
             * @Author: 张金行
     * @Date: 2018/7/26
            *//*
    @PostMapping("findPersonalDetail")
    public Msg findPersonalDetail(Integer userid){
        User user = userService.findPersonalDetail(userid);
        if(user!=null)
            return Msg.success().add("user",user);
        else
            return Msg.fail();
    }*/
    /**
     * @Description: 个人中心个人信息展示（备选1）
     * @Param:
     * @return:
     * @Author: su
     * @Date: 2018/7/29
     */
    @PostMapping("findPersonalDetail")
    public Map<String , Object> findPersonalDetail(String userID){
        Integer userIDInt = Integer.parseInt(userID);
        return userService.findPersonalDetail(userIDInt);
    }

    /**
     * @Description: 个人中心个人信息展示（备选2）
     * @Param:
     * @return:
     * @Author: su
     * @Date: 2018/7/29
     */
//    @PostMapping("findPersonalDetail")
//    public Map<String , Object> findPersonalDetail(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if (null!=user) {
//            int userID = user.getUserId();
//            return userService.findPersonalDetail(userID);
//        }else {
//            return MapUtil.setMap2("1","获取员工id失败",null);
//        }
//    }
}
