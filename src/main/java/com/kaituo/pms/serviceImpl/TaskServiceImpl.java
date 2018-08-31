package com.kaituo.pms.serviceImpl;

import com.github.pagehelper.PageHelper;

import com.kaituo.pms.bean.Integral;
import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.TaskExample;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.bean.UserExample;
import com.kaituo.pms.dao.TaskMapper;
import com.kaituo.pms.dao.UserMapper;

import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.Constant;
import com.kaituo.pms.utils.OutJSON;


import com.kaituo.pms.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: ktpms
 * @description: 有关任务的service
 * @author: 由苏泽华创建
 * @create: 2018-08-09 21:45
 **/
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    IntegralMapper integralMapper;

    /**
     * 验证是否过期，过期则修改数据库中任务状态
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void expiredVerification() {
        try {
            // 查询所有相关任务
            TaskExample example = new TaskExample();
            TaskExample.Criteria criteria = example.createCriteria();
            List<com.kaituo.pms.bean.Task> taskList = taskMapper.selectByExample(example);
            if (null != taskList && taskList.size() > 0) {
                // 循环对比是否过期
                for(com.kaituo.pms.bean.Task task : taskList){

                    // 排除2为任务已经被领取,3为提前取消,4为提交审核，5为任务完成失败,7为任务完成了
                    if (task.getTaskStatus() != Constant.THE_TASK_HAS_BEEN_RECEIVED &&
                            task.getTaskStatus() != Constant.TASK_CANCELDE_IN_ADVANCE &&
                            task.getTaskStatus() != Constant.TASK_SUBMISSION_REVIEW &&
                            task.getTaskStatus() != Constant.TASK_COMPLETION_FAILED &&
                            task.getTaskStatus() != Constant.MISSION_COMPLETED) {
                        // 如果任务过期则修改任务状态
                        if (task.getTaskEndtime().getTime() < System.currentTimeMillis()){
                            task.setTaskStatus(Constant.NO_ONE_ACCEPTS_THE_TASK);
                            TaskExample newExample = new TaskExample();
                            TaskExample.Criteria newEriteria = newExample.createCriteria();
                            newEriteria.andTaskIdEqualTo(task.getTaskId());
                            taskMapper.updateByExample(task , newExample);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    /**
     * 验证是否超时，超时则修改数据库中任务状态
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void timeOutDetection() {
        try {
            // 查询所有相关任务
            TaskExample example = new TaskExample();
            TaskExample.Criteria criteria = example.createCriteria();
            List<com.kaituo.pms.bean.Task> taskList = taskMapper.selectByExample(example);
            if (null != taskList && taskList.size() > 0) {
                // 循环对比是否超时
                for(com.kaituo.pms.bean.Task task : taskList){

                    if (null != task.getTaskGettime()) {
                        long timeLimit = task.getTaskGettime().getTime()+task.getTaskTime() * 60 * 60 * 1000;
                        // 如果任务超时则修改任务状态
                        if (timeLimit< System.currentTimeMillis()){

                            task.setTaskStatus(Constant.TASK_COMPLETION_FAILED);
                            TaskExample newExample = new TaskExample();
                            TaskExample.Criteria newEriteria = newExample.createCriteria();
                            newEriteria.andTaskIdEqualTo(task.getTaskId());
                            taskMapper.updateByExample(task , newExample);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }

    /**
     * 查询指定状态的任务信息
     * @param status :任务状态
     *@Author: 苏泽华
     *@Date: 2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<com.kaituo.pms.bean.Task> listTaskByStatus(int status) {

        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusEqualTo(status);

        return taskMapper.selectByExample(example);
    }

    /**
     * 查询指定状态的任务信息
     * @param status :任务状态
     *@Author: 苏泽华
     *@Date: 2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<com.kaituo.pms.bean.Task> listTaskByStatus(int status , int userId) {

        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusEqualTo(status);
        criteria.andUserIdEqualTo(userId);
        return taskMapper.selectByExample(example);
    }

    /**
     * 获取未完成页面所需的数据
     * @Param:
     * @param userId 员工id
     * @return: java.util.List<com.kaituo.pms.bean.Task>
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Task> listUnfinishedTask(int userId) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusNotEqualTo(Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED);
        criteria.andTaskStatusNotEqualTo(Constant.MISSION_COMPLETED);
        criteria.andUserIdEqualTo(userId);
        return taskMapper.selectByExample(example);
    }

    /**
     * 查询指定状态的任务信息的条数
     *@Description:
     *@param status :任务状态
     *@return long
     *@Author: 苏泽华
     *@Date: 2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long countTaskByStatus(int status) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusEqualTo(status);

        return taskMapper.countByExample(example);
    }

    /**
     * 将获得的任务数据封装成map（待领取）
     * @Description:  将获得的任务数据封装成map
     * @param pageNumber 目标页数
     * @param pageSize 每页条数
     * @param status 任务状态值
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OutJSON getStatesTaskByPage(Integer pageNumber, Integer pageSize, int status) {
        // 如果每页条数为空则将每页条数设为4
        if (null==pageSize){
            pageSize = 4;
        }

        // 条数
        int total = (int)countTaskByStatus(status);
        // 有数据就封装map返回上层
        if (0<total){
            // 分页
            PageHelper.startPage(pageNumber, pageSize);
            List<com.kaituo.pms.bean.Task> list = listTaskByStatus(status);

            Map<String , Object> pageMap = new HashMap<>(2);

            pageMap.put("total" , total);
            pageMap.put("taskList" , list);

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , pageMap);
        }else {
            return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
        }
    }

    /**
     * 将获得的任务数据封装成map(已完成)
     * @Description:  将获得的任务数据封装成map
     * @param pageNumber 目标页数
     * @param pageSize 每页条数
     * @param status 任务状态值
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OutJSON getStatesTaskByPage(Integer pageNumber, Integer pageSize, int status , int userId) {
        // 如果每页条数为空则将每页条数设为4
        if (null==pageSize){
            pageSize = 4;
        }

        // 条数
        int total = (int)countTaskByStatus(status);
        // 有数据就封装map返回上层
        if (0<total){
            // 分页
            PageHelper.startPage(pageNumber, pageSize);
            List<com.kaituo.pms.bean.Task> list = listTaskByStatus(status , userId);

            Map<String , Object> pageMap = new HashMap<>(2);

            pageMap.put("total" , total);
            pageMap.put("taskList" , list);

            return OutJSON.getInstance(CodeAndMessageEnum.COMPLETED_TASK , pageMap);
        }else {
            return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
        }
    }

    /**
     * 获取未完成页面数据并封装好
     * @Description:
     * @Param:
     * @param pageNumber
     * @param pageSize
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OutJSON getUndoneByPage(Integer pageNumber, Integer pageSize , int userId) {
        // 如果每页条数为空则将每页条数设为4
        if (null==pageSize){
            pageSize = 4;
        }

        // 条数
        // 任务的状态,默认为1,1为任务发布成功,2为任务已经被领取,3为提前取消,，4为提交审核，5为任务完成失败,6为任务没人领取,7为任务完成了!56都为任务失效
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusNotEqualTo(Constant.THE_TASK_WAS_SUCCESSFULLY_POSTED);
        criteria.andTaskStatusNotEqualTo(Constant.MISSION_COMPLETED);
        criteria.andUserIdEqualTo(userId);
        int total = taskMapper.countByExample(example);
        // 有数据就封装map返回上层
        if (0<total){
            // 分页
            PageHelper.startPage(pageNumber, pageSize);
            List<Task> list = listUnfinishedTask(userId);

            Map<String , Object> pageMap = new HashMap<>(2);

            pageMap.put("total" , total);
            pageMap.put("taskList" , list);

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , pageMap);
        }else {
            return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
        }
    }

    /**
     * 通过任务id获得对应任务信息
     * @Description:
     * @param taskId 任务id
     * @return: 单个任务对象
     * @Author: 苏泽华
     * @Date: 2018/8/10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public com.kaituo.pms.bean.Task getTask(int taskId) {
        return taskMapper.selectByPrimaryKey(taskId);
    }

    /**
     * 领取任务
     * @Description:
     * @Param:
     * @param task 任务
     * @param user 员工
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OutJSON recieveTheTask(com.kaituo.pms.bean.Task task , User user) {
        try {
            // 修改员工积分操作
            // 员工当前积分
            int oldUserIntegral = user.getUserIntegral();
            // 任务消耗
            int taskPrice = task.getTaskPrice();
            // 变更员工实例中的积分
            user.setUserIntegral(oldUserIntegral-taskPrice);

            // 修改员工积分
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUserIdEqualTo(user.getUserId());
            userMapper.updateByExample(user,userExample);

            // 添加积分记录操作
            Integral integral = new Integral();
            // 积分的对应用户
            integral.setUserId(user.getUserId());
            // 变动前的积分值
            integral.setIntegralStartnum(oldUserIntegral);
            // 积分的变动原因
            integral.setIntegralChangestr("领取任务");
            // 积分的变动时间
            integral.setIntegralTime(new Date(System.currentTimeMillis()));
            // 积分的变动值
            integral.setIntegralChangeint(-taskPrice);
            // 变动后的积分值
            integral.setIntegralEndnum(oldUserIntegral-taskPrice);

            integralMapper.insert(integral);

            // 任务变更
            // 修改任务状态为已领取
            task.setTaskStatus(Constant.THE_TASK_HAS_BEEN_RECEIVED);
            // 用户的ID
            task.setUserId(user.getUserId());
            // 领取该任务的用户的名字
            task.setUserName(user.getUserName());
            // 第一次任务
            task.setTaskNumber(1);
            // 任务领取时间
            task.setTaskGettime(new Date(System.currentTimeMillis()));

            TaskExample taskExample = new TaskExample();
            TaskExample.Criteria taskCriteria = taskExample.createCriteria();
            taskCriteria.andTaskIdEqualTo(task.getTaskId());
            taskMapper.updateByExample(task , taskExample);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(),e);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
        return OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_STATUS_SUCCESS);
    }

    /**
     * 提交审核
     * @param task
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/14
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OutJSON submitReview(Task task) {
        try {
            TaskExample taskExample = new TaskExample();
            TaskExample.Criteria taskCriteria = taskExample.createCriteria();
            taskCriteria.andTaskIdEqualTo(task.getTaskId());
            taskMapper.updateByExample(task , taskExample);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage(),e);
            return OutJSON.getInstance(CodeAndMessageEnum.SUBMIT_TASK_ERROR);
        }
    }

    /**
     * 发布任务
     * @Description:
     * @Param:
     * @param task
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int publishTask(Task task) {
        try {
            return taskMapper.insert(task);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage() , e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取风控中心——任务管理——已发布任务的数据
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OutJSON listPublishedTask(int pageNumber , Integer pageSize) {
        // 如果每页条数为空则将每页条数设为4
        if (null==pageSize){
            pageSize = 4;
        }
        // 总行数
        int total = publishedTaskCount();
        // 有数据就封装map返回上层
        if (0<total){
        // 分页
        PageHelper.startPage(pageNumber, pageSize);
        List<Task> publishedTaskList = taskMapper.listPublishedTask();
        Map<String , Object> pageMap = new HashMap<>(2);

        pageMap.put("total" , total);
        pageMap.put("taskList" , publishedTaskList);

        return OutJSON.getInstance(CodeAndMessageEnum.PUBLISHED_TASK_COMPLETED , pageMap);
        }else {
            return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
        }
    }

    /**
     * 获取风控中心——任务管理——待审核任务的数据
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    @Override
    public OutJSON listPendingTask(int pageNamber , Integer pageSize) {
        // 如果每页条数为空则将每页条数设为4
        if (null==pageSize){
            pageSize = 4;
        }
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andTaskStatusEqualTo(Constant.TASK_SUBMISSION_REVIEW);
        // 总行数
        int total = taskMapper.countByExample(taskExample);
        // 有数据就封装map返回上层
        if (0<total){
            // 分页
            PageHelper.startPage(pageNamber, pageSize);
            List<Task> list = taskMapper.selectByExample(taskExample);
            Map<String , Object> pageMap = new HashMap<>(2);

            pageMap.put("total" , total);
            pageMap.put("taskList" , list);

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , pageMap);
        }else {
            return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
        }
    }

    /**
     * 风控中心——任务管理——失效任务
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    @Override
    public OutJSON lisInvalidTask(int pageNamber , Integer pageSize) {
        // 如果每页条数为空则将每页条数设为4
        if (null==pageSize){
            pageSize = 4;
        }
        // 总行数
        int total = invalidTaskCount();
        // 有数据就封装map返回上层
        if (0<total){
            // 分页
            PageHelper.startPage(pageNamber, pageSize);
            List<Task> invalidTaskList = taskMapper.listInvalidTask();
            Map<String , Object> pageMap = new HashMap<>(2);

            pageMap.put("total" , total);
            pageMap.put("taskList" , invalidTaskList);

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , pageMap);
        }else {
            return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
        }
    }

    /**
     * 获取风控中心——任务管理——已发布任务的数据的总数
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int publishedTaskCount() {
        return taskMapper.publishedTaskCount();
    }


    /**
     * 风控中心——任务管理——失效任务的总数
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int invalidTaskCount() {
        return taskMapper.invalidTaskCount();
    }

    /**
     * 风控中心——任务管理——提前取消任务按钮
     * @Param:
     * @param taskId 任务id
     * @return: int
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelInAdvance(int taskId) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        Task task = new Task();
        task.setTaskStatus(Constant.TASK_CANCELDE_IN_ADVANCE);
        return taskMapper.updateByExampleSelective(task , taskExample);
    }

    /**
     * 审核通过
     * @Param:
     * @param taskId
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean auditPassed(int taskId) {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        User user = userMapper.selectByPrimaryKey(task.getUserId());

        // 判断是否是多次任务
            if(1<task.getTaskCountnumber()){
                // 多次任务

                // 判断是否已经全部完成
                if(task.getTaskNumber().equals(task.getTaskCountnumber())){
                    // 已经全部完成
                    return missionAccomplished(taskId);
                }else{
                    // 没有全部完成

                    // 任务变更
                    // 修改任务状态为已完成
                    task.setTaskStatus(Constant.THE_TASK_HAS_BEEN_RECEIVED);
                    task.setTaskNumber(task.getTaskNumber()+1);
                    TaskExample taskExample = new TaskExample();
                    TaskExample.Criteria taskCriteria = taskExample.createCriteria();
                    taskCriteria.andTaskIdEqualTo(task.getTaskId());
                    taskMapper.updateByExample(task , taskExample);
                    taskMapper.updateByExampleSelective(task , taskExample);
                    // 返回 真，表示成功完成
                    return true;
                }

            }else {
                // 单次任务

                return missionAccomplished(taskId);
            }

    }

    /**
     * 审核驳回
     * @Param:
     * @param taskId
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean auditRejection(int taskId) {
            TaskExample taskExample = new TaskExample();
            TaskExample.Criteria criteria = taskExample.createCriteria();
            criteria.andTaskIdEqualTo(taskId);
            Task task = new Task();
            task.setTaskStatus(Constant.THE_TASK_HAS_BEEN_RECEIVED);
            taskMapper.updateByExampleSelective(task , taskExample);
            return true;
    }

    /**
     * 完成任务
     * @Param:
     * @param taskId
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean missionAccomplished(int taskId) {
            Task task = taskMapper.selectByPrimaryKey(taskId);
            User user = userMapper.selectByPrimaryKey(task.getUserId());

            // 修改员工积分操作
            // 员工当前积分
            int oldUserIntegral = user.getUserIntegral();
            // 任务奖励
            int taskAward = task.getTaskAward();
            // 变更员工实例中的积分
            user.setUserIntegral(oldUserIntegral + taskAward);
            // 修改员工积分
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUserIdEqualTo(user.getUserId());
            userMapper.updateByExample(user,userExample);

            // 添加积分记录操作
            Integral integral = new Integral();
            // 积分的对应用户
            integral.setUserId(user.getUserId());
            // 变动前的积分值
            integral.setIntegralStartnum(oldUserIntegral);
            // 积分的变动原因
            integral.setIntegralChangestr("完成任务");
            // 积分的变动时间
            integral.setIntegralTime(new Date());
            // 积分的变动值
            integral.setIntegralChangeint(taskAward);
            // 变动后的积分值
            integral.setIntegralEndnum(oldUserIntegral + taskAward);
            // 数据库添加记录
            integralMapper.insert(integral);

            // 任务变更
            // 修改任务状态为已完成
            task.setTaskStatus(Constant.MISSION_COMPLETED);
            TaskExample taskExample = new TaskExample();
            TaskExample.Criteria taskCriteria = taskExample.createCriteria();
            taskCriteria.andTaskIdEqualTo(task.getTaskId());
            taskMapper.updateByExample(task , taskExample);
            taskMapper.updateByExampleSelective(task , taskExample);

            // 返回 真，表示成功完成
            return true;
    }

    /**
     * 重新发布
     * @Param:
     * @param task
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean republish(Task task) {
            // 修改任务字段
            // 重置任务次数
            task.setTaskNumber(1);
            // 清空原员工信息
            task.setUserId(null);
            task.setUserName(null);
            // 清空时间
            task.setTaskGettime(null);

            TaskExample taskExample = new TaskExample();
            TaskExample.Criteria taskCriteria = taskExample.createCriteria();
            taskCriteria.andTaskIdEqualTo(task.getTaskId());
            taskMapper.updateByExample(task , taskExample);
            return true;
    }
}
