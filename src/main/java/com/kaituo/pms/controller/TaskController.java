package com.kaituo.pms.controller;

import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.utils.OutJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ktpms
 * @description: 有关任务的controller
 * @author: 由苏泽华创建
 * @create: 2018-08-09 23:00
 **/
@Slf4j
@RestController
public class TaskController {

    @Autowired
    TaskService taskService;
    @GetMapping(value = {"tasks/status/one/{pageNamber}/{pageSize}" , "tasks/status/one/{pageNamber}"})
    public OutJSON findStateTaskByPage(@PathVariable(value = "pageNamber") int pageNamber ,
                                       @PathVariable("pageNamber") int pageSize) {
        //已发布但未被领取的任务为1
        int status = 1;
        //查询所有已失效任务的信息
        //分页
        return taskService.getStatesTaskByPage(pageNamber , pageSize , status);
    }
}
