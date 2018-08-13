package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Integral;
import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @Autowired
    UserService userService;

    @GetMapping(value = {"tasks/status/one/{pageNamber}/{pageSize}" , "tasks/status/one/{pageNamber}"})
    public OutJSON findStateTaskByPage(@PathVariable(value = "pageNamber") int pageNamber ,
                                       @PathVariable("pageNamber") int pageSize) {
        //已发布但未被领取的任务为1
        int status = 1;
        //查询所有已发布但未被领取的任务的信息
        //分页
        return taskService.getStatesTaskByPage(pageNamber , pageSize , status);
    }

    @PutMapping(value = {"tasks/status/one/{taskId}" , "tasks/status/one/{taskId}/{userId}"})
    public OutJSON recieveTheTask(HttpServletRequest request , @PathVariable(value = "taskId") int taskId ,
                                  @PathVariable(value = "userId" , required = false) Integer userId){

        try{

            User user = null;
            if (null==userId){
                HttpSession session = request.getSession();
                user = (User) session.getAttribute("user");
            }else{
                // 通过员工id找到员工个人信息
                user = userService.getUserFromTable(userId);
            }

            // 通过任务id找到任务信息
            Task task = taskService.getTask(taskId);
            // 判断是否取得了user和task
            if(null!=user && null!=task){

                // 检查是否超过结束时间
                if (task.getTaskEndtime().getTime()<System.currentTimeMillis()) {

                    // 如果任务状态为1（已发布）则返回消息
                    if (1 == task.getTaskStatus()){

                        // 如果用户的积分大于等于任务消耗积分则扣除积分添加积分明细修改任务状态
                        if (user.getUserIntegral()>=task.getTaskPrice()){

                            // 数据的修改
                            return taskService.recieveTheTask(task , user);

                        }

                    }else {
                        return  OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_STATUS_NOT_ONE);
                    }

                } else {
                    return OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_STATUS_TIME_OUT);
                }

            }else {
                return OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_USER_OR_TASK_NULL);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
        return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }
}
