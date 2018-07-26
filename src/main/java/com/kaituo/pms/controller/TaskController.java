package com.kaituo.pms.controller;



import com.github.pagehelper.PageHelper;
import com.kaituo.pms.domain.Page;
import com.kaituo.pms.domain.Task;
import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.MapUtil;
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
    /** 
     *@Description: 任务列表-待领取-分页查询
     *@Param: String pageNumber,String pageSize
     *@return: 
     *@Author: 郭士伟
     *@Date: 2018/7/24
    */
    @ResponseBody
    @RequestMapping(value = "findTaskByPage",method = RequestMethod.POST)
    public Map<String,Object> findTaskByPage(@RequestParam int pageNumber, @RequestParam int pageSize){

        //待领取任务的状态为1
        int status = 1;
        //查询所有待领取的任务总数
        int total = (int) taskService.findNumberOfTaskByStatus(status);
        //查询所有待领取任务的信息
        //分页
        PageHelper.startPage(Integer.valueOf(pageNumber),pageSize);
        List<Task> list = taskService.findTaskByStatus(status);
        //封装map
        Map<String,Object> data = new HashMap<>();
        data.put("total",total);//待领取任务的总数
        data.put("Task",list);//待领取任务的数据
        Map<String,Object> map = MapUtil.setMap2("1","成功",data);
       return map;
    }

   /** 
    *@Description:风控中心-任务管理-发布任务（新增任务）
    *@Author: 郭士伟
    *@Date: 2018/7/25
   */
   @ResponseBody
   @RequestMapping(value = "addTask",method = RequestMethod.POST)
   public Map<String,Object> addTask(Task task){
        //直接将新增的任务存进去就好啦~~~
       int taskId = taskService.addTask(task);
        if(taskId > 0){
            return MapUtil.setMap2("1","添加成功",null);
        }
       return MapUtil.setMap2("0","添加失败",null);
   }


   }
