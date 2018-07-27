package com.kaituo.pms.controller;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.domain.Page;
import com.kaituo.pms.domain.Task;
import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.MapUtil;
import com.kaituo.pms.utils.Msg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.JavaUtilZipFileAccess;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 *@Description:
 *@Param: 
 *@return: 
 *@Author: 郭士伟
 *@Date: 2018/7/24
*/
@RestController
@RequestMapping(value = "task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;
    /*
     * 状态1 待领取
     * 状态2 已领取
     * 状态3 待审核
     * 状态4 已发布
     * 状态5 已完成 未完成 提前取消 时间过期
     * */

    /**
     * @Description:风控中心-任务管理-发布任务（新增任务） 状态1
     * @Author: 郭士伟
     * @Date: 2018/7/25
     */
    @ResponseBody
    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    public Msg addTask(Task task) {
        //直接将新增的任务存进去就好啦~~~
        int taskId = taskService.addTask(task);
        if (taskId > 0) {
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * @Description: 任务列表-待领取-分页查询
     * @Param: String pageNumber,String pageSize
     * @return:
     * @Author: 郭士伟
     * @Date: 2018/7/24
     */
    @ResponseBody
    @RequestMapping(value = "findState1TaskByPage", method = RequestMethod.POST)
    public Msg findStateTaskByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        //待领取（已发布）任务的状态为1
        int status = 1;
        //查询所有待领取任务的信息
        //分页
        PageHelper.startPage(pn, 5);
        List<Task> list = taskService.findTaskByStatus(status);
        //封装map
        PageInfo pageInfo = new PageInfo(list, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }
    /**
     * @Description: 任务列表-已领取-分页查询
     * @Param: String pageNumber,String pageSize
     * @return:
     * @Author: 郭士伟
     * @Date: 2018/7/24
     */
    @ResponseBody
    @RequestMapping(value = "findState2TaskByPage", method = RequestMethod.POST)
    public Msg findState2TaskByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        //待领取（已发布）任务的状态为1
        int status = 2;
        //查询所有待领取任务的信息
        //分页
        PageHelper.startPage(pn, 5);
        List<Task> list = taskService.findTaskByStatus(status);
        //封装map
        PageInfo pageInfo = new PageInfo(list, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }
    /**
     * @Description:风控中心-任务管理-已发布任务 状态4
     * @Author: 张金行
     * @Date: 2018/7/27
     */
    @ResponseBody
    @RequestMapping(value = "findAllTaskByPage", method = RequestMethod.POST)
    /*已发布任务就是待领取任务*/
    public Msg findAllTaskByPage() {


        return Msg.success();
    }



}


