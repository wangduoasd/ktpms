package com.kaituo.pms.service;

import com.kaituo.pms.domain.Task;

import java.util.List;

public interface TaskService {
    /**
     *@Description: 查询所有待领取任务的数量
     *@Author: 郭士伟
     *@Date: 2018/7/24
    */
    long findNumberOfTaskByStatus(int status);

    /**
     *@Description: 查询所有待领取任务的信息
     *@Author: 郭士伟
     *@Date: 2018/7/24
     */
    List<Task> findTaskByStatus(int status);

    /** 
     *@Description: 新增任务信息
     *@Param: 
     *@return: 
     *@Author: 郭士伟
     *@Date: 2018/7/26
    */
    int addTask(Task task);
}
