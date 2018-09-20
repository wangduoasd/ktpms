package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    TokenService tokenService;

    @Autowired
    RoleService roleService;

    /**
     * 分页查寻任务
     * @param callPage 调用的页面
     * @param pageNamber 目标页面
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @GetMapping("tasks/status/list/{callPage}/{pageNumber}/u/{token}")
    public OutJSON findCollectionStatus(@PathVariable("callPage") String callPage ,
                                        @PathVariable(value = "token") String token,
                                        @PathVariable(value = "pageNumber") int pageNamber ) {
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        if (null == token1.getUserId()){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }

        int userId = token1.getUserId();

        try {
            switch (callPage){
                // 未领取页面调用
                case Constant.MISSION_CENTER_TASK_LIST_PENDING:
                    //已发布但未被领取的任务为1
                    int status = Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED;
                    // 处理过期数据
                    taskService.expiredVerification();
                    // 获得user对象
                    User user = userService.findUserById(userId);
                    // 判断员工状态，如果积分冻结或者离职不可领取任务
                    if(user.getUserStatus().equals(1) ||
                            user.getUserStatus().equals(4) ||
                            user.getUserStatus().equals(5)){
                        return OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_USER_OR_TASK_NULL);
                    }
                    //查询所有已发布但未被领取的任务的信息
                    //分页
                    return taskService.getPendingTaskByPage(pageNamber , null , status);
                // 未完成页面调用
                case Constant.MISSION_CENTER_TASK_LIST_UNDONE:
                    // 处理超时数据
                    taskService.timeOutDetection();
                    //查询所有已发布但未被领取的任务的信息
                    //分页
                    return taskService.getUndoneByPage(pageNamber , null , userId);
                // 已完成页面调用
                case Constant.MISSION_CENTER_TASK_LIST_COMPLETED:
                    //查询所有当前id的已完成数据
                    //分页
                    return taskService.getStatesTaskByPage(pageNamber , null , Constant.MISSION_COMPLETED , userId);
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
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @PutMapping( "tasks/status/one/{taskId}")
    public OutJSON recieveTheTask(@PathVariable(value = "taskId") int taskId){
        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        if (null == token1.getUserId()){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }

        int userId = token1.getUserId();

        try{

            User user =  userService.getUserFromTable(userId);

            // 处理过期数据
            taskService.expiredVerification();

            // 通过任务id找到任务信息
            Task task = taskService.getTask(taskId);
            // 判断是否取得了user和task
            if(null!=user && null!=task){
                if(task.getTaskStarttime().getTime() > System.currentTimeMillis()){
                    return OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_STATUS_TIME_NOT_START);
                }
                // 检查是否超过结束时间
                if (task.getTaskEndtime().getTime() > System.currentTimeMillis()) {

                    // 如果任务状态为1（已发布）则返回消息
                    if (Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED == task.getTaskStatus()){

                        // 如果用户的积分大于等于任务消耗积分则扣除积分添加积分明细修改任务状态
                        if (user.getUserIntegral()>=task.getTaskPrice()){

                            // 任务已被领取时，再次领取
                            if(task.getTaskStatus() == Constant.THE_TASK_HAS_BEEN_RECEIVED){
                                return OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_STATUS_IS_RECEIVED);
                            }
                            // 数据的修改
                            return taskService.recieveTheTask(task , user , token);

                        }else {
                            return OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_INSUFFICIENT_POINTS);
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
        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        if (null == token1.getUserId()){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        int userId = token1.getUserId();

        Task task = taskService.getTask(taskId);
        if (task.getTaskStatus() == Constant.THE_TASK_HAS_BEEN_RECEIVED) {
            task.setTaskStatus(Constant.TASK_SUBMISSION_REVIEW);
            return taskService.submitReview(task);
        }
        return OutJSON.getInstance(CodeAndMessageEnum.SUBMIT_TASK_ERROR , null);
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
    @PostMapping("authority/five/tasks/status")
    public OutJSON publishTask(MultipartFile file,String starttime , String endtime , Task task){
        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        // 权限控制

        if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
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
                url = url.replace(Util.seperator , "/");
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
                    return OutJSON.getInstance(CodeAndMessageEnum.THE_TASK_WAS_SUCCESSFULLY_POSTDE , null);
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
    @GetMapping(value = {"authority/five/tasks/management/{callPage}/{pageNamber}/{pageSize}/{token:.+}" ,
            "authority/five/tasks/management/{callPage}/{pageNamber}/{token:.+}"})
    public OutJSON getManagementPagination(@PathVariable("callPage") String callPage ,
                                        @PathVariable(value = "pageNamber") int pageNamber ,
                                        @PathVariable(value = "pageSize" , required = false) Integer pageSize ,
                                        @PathVariable(value = "token") String token) {
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        // 权限控制

        if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
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
    @PutMapping("authority/five/task/{taskId}")
    public OutJSON cancelInAdvance(@PathVariable("taskId") int taskId){

        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }

        // 权限控制
        if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }

        if (0<taskService.cancelInAdvance(taskId)) {
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , null);
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
    @PostMapping("authority/five/task/{taskId}/{auditType}")
    public OutJSON auditTask(@PathVariable("taskId") int taskId ,
                             @PathVariable("auditType") int auditType){

        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        // 权限控制

        if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        switch (auditType){
            case Constant.AUDIT_PASSED :
                try {
                    if(taskService.auditPassed(taskId)){
                        return OutJSON.getInstance(CodeAndMessageEnum.AUDIT_PASSED_SUCCESS , null);
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
                        return OutJSON.getInstance(CodeAndMessageEnum.AUDIT_REJECTION_SUCCESS , null);
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
     * 重新发布
     * @Param:
     * @param file 图片
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @param task 任务对象
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    @PutMapping("authority/five/task/again")
    public OutJSON republish(MultipartFile file,String starttime , String endtime , Task task){

        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        // 权限控制

        if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
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
                    return OutJSON.getInstance(CodeAndMessageEnum.THE_TASK_WAS_SUCCESSFULLY_POSTDE ,null);
                }else{
                    return OutJSON.getInstance(CodeAndMessageEnum.TASK_POSTING_FAILED);
                }
            // 如果是1则为上传成功
            case Constant.IMG_UPLOSD_SUCCESS :
                // 获取相对路径
                String url = (String) map.get("url");
                url = url.replace(Util.seperator , "/");
                // 数据处理
                task.setTaskStarttime(startDate);
                task.setTaskEndtime(endDate);
                task.setTaskImage(url);
                task.setTaskStatus(Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED);
                task.setTaskNumber(0);
                // 数据库添加数据，如果失败则返回任务发布失败，成功则返回任务已发布
                if (taskService.republish(task)){
                    return OutJSON.getInstance(CodeAndMessageEnum.THE_TASK_WAS_SUCCESSFULLY_POSTDE , null);
                }else{
                    return OutJSON.getInstance(CodeAndMessageEnum.TASK_POSTING_FAILED);
                }
            default:
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR , "未知原因错误");
        }
    }

    /**
     * 通过任务id获取任务对象
     * @Param:
     * @param taskId
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    @GetMapping("authority/five/task/again/{taskId}/{token:.+}")
    public OutJSON getTask(@PathVariable("taskId") int taskId ,
                           @PathVariable(value = "token") String token){
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        // 权限控制

        if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        try {
            Task task = taskService.getTask(taskId);
            if(null == task){
                return OutJSON.getInstance(CodeAndMessageEnum.TASK_NOT_FOUND);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , task);
        } catch (Exception e) {
            log.error("getTask=>" + e.getMessage() , e);
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
