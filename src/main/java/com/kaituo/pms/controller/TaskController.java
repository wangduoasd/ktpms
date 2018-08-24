package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.Constant;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @program: ktpms
 * @description: 有关任务的controller
 * @author: 由苏泽华创建
 * @create: 2018-08-09 23:00
 **/
@Slf4j
@RestController
@CrossOrigin
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    /**
     * 分页查寻任务
     * @param callPage 调用的页面
     * @param userId 员工id
     * @param pageNumber 目标页面
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @GetMapping("task/status/list/{callPage}/{pageNumber}")
    public OutJSON findCollectionStatus(@PathVariable("callPage") String callPage ,
                                        @SessionAttribute("userId") Integer userId,
                                        @PathVariable(value = "pageNumber") int pageNumber) {
        if (null == userId){
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }

        try {
            switch (callPage){
                // 未领取页面调用
                case Constant.MISSION_CENTER_TASK_LIST_PENDING:
                    //已发布但未被领取的任务为1
                    int status = Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED;
                    // 处理过期数据
                    taskService.expiredVerification();
                    //查询所有已发布但未被领取的任务的信息
                    //分页
                    return taskService.getStatesTaskByPage(pageNumber , null , status);
                // 未完成页面调用
                case Constant.MISSION_CENTER_TASK_LIST_UNDONE:
                    // 处理超时数据
                    taskService.timeOutDetection();
                    //查询所有已发布但未被领取的任务的信息
                    //分页
                    return taskService.getUndoneByPage(pageNumber , null , userId);
                // 已完成页面调用
                case Constant.MISSION_CENTER_TASK_LIST_COMPLETED:
                    //查询所有当前id的已完成数据
                    //分页
                    return taskService.getStatesTaskByPage(pageNumber , null , Constant.MISSION_COMPLETED , userId);
                default:
                    return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR,"调用页面错误");
        }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 分页查寻任务(零时)
     * @param callPage 调用的页面
     * @param userId 员工id
     * @param pageNamber 目标页面
     * @param pageSize 每页条数
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @GetMapping("tasks/status/list/{callPage}/{pageNamber}/{userId}")
    public OutJSON findCollectionStatus(@PathVariable("callPage") String callPage ,
                                        @PathVariable("userId") int userId,
                                        @PathVariable(value = "pageNamber") int pageNamber ,
                                        @PathVariable(value = "pageSize" , required = false) int pageSize) {

        try {
            switch (callPage){
                // 未领取页面调用
                case Constant.MISSION_CENTER_TASK_LIST_PENDING:
                    //已发布但未被领取的任务为1
                    int status = Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED;
                    // 处理过期数据
                    taskService.expiredVerification();
                    //查询所有已发布但未被领取的任务的信息
                    //分页
                    return taskService.getStatesTaskByPage(pageNamber , pageSize , status);
                // 未完成页面调用
                case Constant.MISSION_CENTER_TASK_LIST_UNDONE:
                    // 处理超时数据
                    taskService.timeOutDetection();
                    //查询所有已发布但未被领取的任务的信息
                    //分页
                    return taskService.getUndoneByPage(pageNamber , pageSize , userId);
                // 已完成页面调用
                case Constant.MISSION_CENTER_TASK_LIST_COMPLETED:
                    //查询所有当前id的已完成数据
                    //分页
                    return taskService.getStatesTaskByPage(pageNamber , pageSize , Constant.MISSION_COMPLETED , userId);
                default:
                    return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR,"调用页面错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 领取任务
     * @Param:
     * @param taskId 任务id
     * @param userId 员工id
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @PutMapping("tasks/status/one/{taskId}")
    public OutJSON recieveTheTask(@PathVariable(value = "taskId") int taskId ,
                                  @SessionAttribute("userId") Integer userId){

        try{

            User user =  userService.getUserFromTable(userId);

            // 处理过期数据
            taskService.expiredVerification();

            // 通过任务id找到任务信息
            Task task = taskService.getTask(taskId);
            // 判断是否取得了user和task
            if(null!=user && null!=task){

                // 检查是否超过结束时间
                if (task.getTaskEndtime().getTime() <= System.currentTimeMillis()) {

                    // 如果任务状态为1（已发布）则返回消息
                    if (Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED == task.getTaskStatus()){

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

    /**
     * 领取任务(零时)
     * @Param:
     * @param taskId 任务id
     * @param userId 员工id
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @PutMapping( "tasks/status/one/{taskId}/{userId}")
    public OutJSON recieveTheTask(@PathVariable(value = "taskId") int taskId ,
                                  @PathVariable("userId") int userId){

        try{

            User user =  userService.getUserFromTable(userId);

            // 处理过期数据
            taskService.expiredVerification();

            // 通过任务id找到任务信息
            Task task = taskService.getTask(taskId);
            // 判断是否取得了user和task
            if(null!=user && null!=task){

                // 检查是否超过结束时间
                if (task.getTaskEndtime().getTime() <= System.currentTimeMillis()) {

                    // 如果任务状态为1（已发布）则返回消息
                    if (Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED == task.getTaskStatus()){

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

    /**
     * 提交任务
     * @Param:
     * @param taskId
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/14
     */
    @PutMapping("tasks/status/four/{taskId}")
    public OutJSON submitTask(@PathVariable("taskId") Integer taskId){
        Task task = taskService.getTask(taskId);
        if (task.getTaskStatus() == Constant.THE_TASK_HAS_BEEN_RECEIVED) {
            task.setTaskStatus(Constant.TASK_SUBMISSION_REVIEW);
            return taskService.submitReview(task);
        }
        return OutJSON.getInstance(CodeAndMessageEnum.SUBMIT_TASK_ERROR);
    }

    /**
     * 发布任务
     * @Param:
     * @param file
     * @param starttime
     * @param endtime
     * @param task
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    @PostMapping("tasks/status")
    public OutJSON publishTask(MultipartFile file,String starttime , String endtime , Task task){
        // 图片上传并获取上传的状态
        Map<String, Object> map = Util.imgUpload(file , Util.getImgRelativePath());

        // 上传的状态码
        int key = (int)map.get("code");
        switch (key){

            // 如果是零
            case Constant.IMG_UPLOSD_ERROR :
                // 返回图片上传失败
                return OutJSON.getInstance(CodeAndMessageEnum.PUBLISHING_TASK_IMAGE_HAS_BEEN_SUCCESSFULLY_UPLOADED);
            // 如果是2
            case Constant.IMG_UPLOSD_EMPTY :
                // 返回图片为空
                return OutJSON.getInstance(CodeAndMessageEnum.PUBLISHING_TASK_IMAGE_IS_EMPTY);
            // 如果是1则为上传成功
            case Constant.IMG_UPLOSD_SUCCESS :
                // 获取相对路径
                String url = (String) map.get("url");
                // 时间处理
                Date startDate = Util.stampToDate(starttime);
                Date endDate = Util.stampToDate(endtime);
                // 数据处理
                task.setTaskStarttime(startDate);
                task.setTaskEndtime(endDate);
                task.setTaskImage(url);
                task.setTaskStatus(Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED);
                task.setTaskNumber(0);
                // 数据库添加数据，如果失败则返回任务发布失败，成功则返回任务已发布
                if (taskService.publishTask(task) == 1){
                    return OutJSON.getInstance(CodeAndMessageEnum.THE_TASK_WAS_SUCCESSFULLY_POSTDE);
                }else{
                    return OutJSON.getInstance(CodeAndMessageEnum.TASK_POSTING_FAILED);
                }
            default:
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR , "未知原因错误");
        }
    }

    /**
     * 任务管理,分页查寻任务
     * @param callPage 调用的页面
     * @param pageNamber 目标页面
     * @param pageSize 每页条数
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @GetMapping(value = {"tasks/management/{callPage}/{pageNamber}/{pageSize}" ,
            "tasks/management/{callPage}/{pageNamber}"})
    public OutJSON getManagementPagination(@PathVariable("callPage") String callPage ,
                                        @PathVariable(value = "pageNamber") int pageNamber ,
                                        @PathVariable(value = "pageSize" , required = false) int pageSize) {
        try {

            // 处理过期数据
            taskService.expiredVerification();
            // 处理超时数据
            taskService.timeOutDetection();

            switch (callPage){
                // 已发布任务调用
                case Constant.RISK_CONTROL_CENTER_TASK_MANAGEMENT_RELEASED_TASK:
                    //查询所有已发布任务的信息
                    //分页
                    return taskService.listPublishedTask(pageNamber , pageSize);
                // 待审核页面调用
                case Constant.RISK_CONTROL_CENTER_TASK_MANAGEMENT_PENDING_TASK:

                    //查询所有状态为待审核的任务的信息
                    //分页
                    return taskService.listPendingTask(pageNamber , pageSize);
                // 失效任务页面调用
                case Constant.RISK_CONTROL_CENTER_TASK_MANAGEMENT_INVALID_TASK:
                    //查询所有当前id的已完成数据
                    //分页
                    return taskService.lisInvalidTask(pageNamber , pageSize);
                default:
                    return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR,"调用页面错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 提前取消任务
     * @Param:
     * @param taskId
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    @PutMapping("task/{taskId}")
    public OutJSON cancelInAdvance(@PathVariable("taskId") int taskId){
        if (0<taskService.cancelInAdvance(taskId)) {
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } else {
            return OutJSON.getInstance(CodeAndMessageEnum.TASK_CANCELED_IN_ADVANCE);
        }

    }

    /**
     * 任务审核
     * @Param:
     * @param taskId
     * @param auditType
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    @PostMapping("task/{taskId}/{auditType}")
    public OutJSON auditTask(@PathVariable("taskId") int taskId ,
                             @PathVariable("auditType") int auditType){
        switch (auditType){
            case Constant.AUDIT_PASSED :
                try {
                    if(taskService.auditPassed(taskId)){
                        return OutJSON.getInstance(CodeAndMessageEnum.AUDIT_PASSED_SUCCESS);
                    }else {
                        return OutJSON.getInstance(CodeAndMessageEnum.AUDIT_PASSED_ERROR);
                    }
                } catch (Exception e) {
                    log.error("auditPassed ==>"+e.getMessage() , e);
                    e.printStackTrace();
                    return OutJSON.getInstance(CodeAndMessageEnum.AUDIT_PASSED_ERROR);
                }
            case Constant.AUDIT_REJECTION:
                try {
                    if (taskService.auditRejection(taskId)){
                        return OutJSON.getInstance(CodeAndMessageEnum.AUDIT_REJECTION_SUCCESS);
                    }else {
                        return OutJSON.getInstance(CodeAndMessageEnum.AUDIT_REJECTION_ERROR);
                    }
                } catch (Exception e) {
                    log.error("auditRejection ==>"+e.getMessage() , e);
                    e.printStackTrace();
                    return OutJSON.getInstance(CodeAndMessageEnum.AUDIT_REJECTION_ERROR);
                }
            default:
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }

    }

    /**
     * 重新发布任务
     * @Param:
     * @param file
     * @param starttime
     * @param endtime
     * @param task
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/24
     */
    @PutMapping("task/again")
    public OutJSON republish(MultipartFile file,String starttime , String endtime , Task task){
        // 图片上传并获取上传的状态
        Map<String, Object> map = Util.imgUpload(file , Util.getImgRelativePath());

        // 时间处理
        Date startDate = Util.stampToDate(starttime);
        Date endDate = Util.stampToDate(endtime);

        // 上传的状态码
        int key = (int)map.get("code");
        switch (key){

            // 如果是零
            case Constant.IMG_UPLOSD_ERROR :
                // 返回图片上传失败
                return OutJSON.getInstance(CodeAndMessageEnum.PUBLISHING_TASK_IMAGE_HAS_BEEN_SUCCESSFULLY_UPLOADED);
            // 如果是2 图片为空
            case Constant.IMG_UPLOSD_EMPTY :
                Task oldTask = taskService.getTask(task.getTaskId());
                // 数据处理
                task.setTaskStarttime(startDate);
                task.setTaskEndtime(endDate);
                task.setTaskImage(oldTask.getTaskImage());
                task.setTaskStatus(Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED);
                task.setTaskNumber(0);
                // 数据库添加数据，如果失败则返回任务发布失败，成功则返回任务已发布
                if (taskService.republish(task)){
                    return OutJSON.getInstance(CodeAndMessageEnum.THE_TASK_WAS_SUCCESSFULLY_POSTDE);
                }else{
                    return OutJSON.getInstance(CodeAndMessageEnum.TASK_POSTING_FAILED);
                }
            // 如果是1则为上传成功
            case Constant.IMG_UPLOSD_SUCCESS :
                // 获取相对路径
                String url = (String) map.get("url");
                // 数据处理
                task.setTaskStarttime(startDate);
                task.setTaskEndtime(endDate);
                task.setTaskImage(url);
                task.setTaskStatus(Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED);
                task.setTaskNumber(0);
                // 数据库添加数据，如果失败则返回任务发布失败，成功则返回任务已发布
                if (taskService.republish(task)){
                    return OutJSON.getInstance(CodeAndMessageEnum.THE_TASK_WAS_SUCCESSFULLY_POSTDE);
                }else{
                    return OutJSON.getInstance(CodeAndMessageEnum.TASK_POSTING_FAILED);
                }
            default:
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR , "未知原因错误");
        }
    }
}
