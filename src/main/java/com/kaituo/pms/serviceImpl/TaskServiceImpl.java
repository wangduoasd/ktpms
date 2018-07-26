package com.kaituo.pms.serviceImpl;


import com.github.pagehelper.PageHelper;
import com.kaituo.pms.dao.TaskMapper;
import com.kaituo.pms.domain.Task;
import com.kaituo.pms.domain.TaskExample;
import com.kaituo.pms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;
    /**
     *@Description: 查询所有待领取任务的数量
     *@Author: 郭士伟
     *@Date: 2018/7/24
     */
    @Override
    public long findNumberOfTaskByStatus(int status) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusEqualTo(status);

        return taskMapper.countByExample(example);
    }
    /**
     *@Description: 查询所有待领取任务的信息
     *@Author: 郭士伟
     *@Date: 2018/7/24
     */
    @Override
    public List<Task> findTaskByStatus(int status) {

        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusEqualTo(status);

        return taskMapper.selectByExample(example);
    }
    /**
     *@Description: 新增任务信息
     *@Author: 郭士伟
     *@Date: 2018/7/26
     */
    @Override
    public int addTask(Task task) {
        return taskMapper.insertSelective(task);
    }
}
