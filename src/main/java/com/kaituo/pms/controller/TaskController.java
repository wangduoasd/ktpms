package com.kaituo.pms.controller;



import com.github.pagehelper.PageHelper;
import com.kaituo.pms.domain.Page;
import com.kaituo.pms.domain.Task;
import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    /** 
     *@Description: 任务列表-待领取-分页查询
     *@Param: String pageNumber,String pageSize
     *@return: 
     *@Author: 郭士伟
     *@Date: 2018/7/24
    */
    @ResponseBody
    @RequestMapping(value = "findTaskByPage",method = RequestMethod.GET)
    public Map<String,Object> findTaskByPage(String page,String limit){
        //将传递过来的参数转为
        int pageNumber = Integer.valueOf(page);
        int pageSize = Integer.valueOf(limit);
        //待领取任务的状态为1
        int status = 1;
        //查询所有待领取的任务总数
        int total = (int) taskService.findNumberOfTaskByStatus(status);
        //查询所有待领取任务的信息
        //分页
        PageHelper.startPage(pageNumber,pageSize);
        List<Task> list = taskService.findTaskByStatus(status);
        //封装map
        Map<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("Task",list);
        Map<String,Object> map = MapUtil.setMap2("1","成功",data);
       return map;
    }

}
