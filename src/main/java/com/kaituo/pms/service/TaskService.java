package com.kaituo.pms.service;

import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.utils.OutJSON;

import java.util.List;
import java.util.Map;

public interface TaskService {

    /**
     * 查询指定状态的任务信息
     *@Description:
     * @param status :任务状态
     *@Author: 苏泽华
     *@Date: 2018/8/9
     */
    List<Task> listTaskByStatus(int status);

    /**
     * 查询指定状态的任务信息的条数
     *@Description:
     *@param status :任务状态
     *@return long
     *@Author: 苏泽华
     *@Date: 2018/8/9
     */
    long countTaskByStatus(int status);

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
    OutJSON getStatesTaskByPage(Integer pageNamber , Integer pageSize , int status);

    /**
     * 通过任务id获得对应任务信息
     * @Description:
     * @param taskId 任务id
     * @return: 单个任务对象
     * @Author: 苏泽华
     * @Date: 2018/8/10 
    */ 
    Task getTask(int taskId);
    
    /**
     *领取任务
     * @Description:
     * @Param:
     * @param task
     * @param user
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/10
    */

    OutJSON recieveTheTask(Task task , User user);
}
