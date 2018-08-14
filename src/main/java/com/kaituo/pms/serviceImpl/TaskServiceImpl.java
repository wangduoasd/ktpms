package com.kaituo.pms.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.kaituo.pms.bean.*;
import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.dao.TaskMapper;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
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
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    IntegralMapper integralMapper;

    /** 
    * @Description:
    * @Param:  
    * @return:  
    * @Author: 苏泽华
    * @Date: 2018/8/9 
    */ 
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Task> listTaskByStatus(int status) {

        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusEqualTo(status);

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
     * 将获得的任务数据封装成map
     * @Description:  将获得的任务数据封装成map
     * @param pageNamber 目标页数
     * @param pageSize 每页条数
     * @param status 任务状态值
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OutJSON getStatesTaskByPage(Integer pageNamber, Integer pageSize, int status) {
        // 如果每页条数为空则将每页条数设为4
        if (null==pageSize){
            pageSize = 4;
        }

        // 条数
        int total = (int)countTaskByStatus(status);
        // 有数据就封装map返回上层
        if (0<total){
            // 分页
            PageHelper.startPage(pageNamber, pageSize);
            List<Task> list = listTaskByStatus(status);

            Map<String , Object> pageMap = new HashMap<>(2);

            pageMap.put("total:" , total);
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
    public Task getTask(int taskId) {
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
    public OutJSON recieveTheTask(Task task , User user) {
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
            integral.setIntegralTime(new Date());
            // 积分的变动值
            integral.setIntegralChangeint(-taskPrice);
            // 变动后的积分值
            integral.setIntegralEndnum(oldUserIntegral-taskPrice);

            integralMapper.insert(integral);

            // 任务变更
            // 修改任务状态为已领取
            task.setTaskStatus(2);
            // 用户的ID
            task.setUserId(user.getUserId());
            // 领取该任务的用户的名字
            task.setUserName(user.getUserName());
            // 任务领取时间
            task.setTaskGettime(new Date());

            TaskExample taskExample = new TaskExample();
            TaskExample.Criteria Taskcriteria = taskExample.createCriteria();
            Taskcriteria.andTaskIdEqualTo(task.getTaskId());
            taskMapper.updateByExample(task , taskExample);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return OutJSON.getInstance(CodeAndMessageEnum.RECIEVE_THE_TASK_STATUS_SUCCESS);
    }
}
