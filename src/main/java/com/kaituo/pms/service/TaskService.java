package com.kaituo.pms.service;

import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.utils.OutJSON;

import java.util.List;
import java.util.Map;

public interface TaskService {

    /**
     * 验证是否过期，过期则修改数据库中任务状态
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    void expiredVerification();

    /**
     * 验证是否超时，超时则修改数据库中任务状态
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    void timeOutDetection();

    /**
     * 查询指定状态的任务信息
     *@Description:
     * @param status :任务状态
     *@Author: 苏泽华
     *@Date: 2018/8/9
     */
    List<Task> listTaskByStatus(int status,int or);

    /**
     * 查询指定状态的任务信息(已完成)
     *@Description:
     * @param status :任务状态
     *@Author: 苏泽华
     *@Date: 2018/8/9
     */
    List<Task> listTaskByStatus(int status , Integer userId);

    /**
     * 获取未完成页面所需的数据
     * @Param:
     * @param userId 员工id
     * @return: java.util.List<com.kaituo.pms.bean.Task>
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    List<Task> listUnfinishedTask(int userId,int status);

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
     * 查询指定状态指定对象的任务信息的条数
     *@Description:
     *@param status :任务状态
     *@return long
     *@Author: 苏泽华
     *@Date: 2018/8/9
     */
    long countTaskByStatus(int status , int userId);

    /**
     * 将获得的任务数据封装成map（待领取）
     * @Description:  将获得的任务数据封装成map
     * @param pageNamber 目标页数
     * @param pageSize 每页条数
     * @param status 任务状态值
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    OutJSON getPendingTaskByPage(Integer pageNamber , Integer pageSize , int status,Integer or);
    /**
     * 将获得的任务数据封装成map(已完成)
     * @Description:  将获得的任务数据封装成map
     * @param pageNamber 目标页数
     * @param pageSize 每页条数
     * @param status 任务状态值
     * @param userId
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    OutJSON getStatesTaskByPage(Integer pageNamber , Integer pageSize , int status , int userId);

    /**
     * 获取未完成页面数据并封装好
     * @Description:
     * @Param:
     * @param pageNamber
     * @param pageSize
     * @param userId
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/13
     */
    OutJSON getUndoneByPage(Integer pageNamber, Integer pageSize , int userId,int status);

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

    OutJSON recieveTheTask(Task task , User user , String token);

    /**
     * 提交审核
     * @param task
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/14
     */
    OutJSON submitReview(Task task);

    /**
     * 发布任务
     * @Description:
     * @Param:
     * @param:task
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/15
     */
    int publishTask(Task task) ;


    /**
     * 获取风控中心——任务管理——已发布任务的数据
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    OutJSON listPublishedTask(int pageNamber , Integer pageSize);

    /**
     * 获取风控中心——任务管理——待审核任务的数据
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    OutJSON listPendingTask(int pageNamber , Integer pageSize,int num);

    /**
     * 风控中心——任务管理——失效任务
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    OutJSON lisInvalidTask(int pageNamber , Integer pageSize);

    /**
     * 获取风控中心——任务管理——已发布任务的数据的总数
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    int publishedTaskCount();

    /**
     * 风控中心——任务管理——失效任务的总数
     * @Param:
     * @param
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    int invalidTaskCount();

    /**
     * 风控中心——任务管理——提前取消任务按钮
     * @Param:
     * @param taskId 任务id
     * @return: int
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    int cancelInAdvance(int taskId);

    /**
     * 审核通过
     * @Param:
     * @param taskId
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    boolean auditPassed(int taskId) ;

    /**
     * 审核驳回
     * @Param:
     * @param taskId
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    boolean auditRejection(int taskId);

    /**
     * 完成任务
     * @Param:
     * @param taskId
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    boolean missionAccomplished(int taskId);

    /**
     * 重新发布
     * @Param:
     * @param task
     * @return: boolean
     * @Author: 苏泽华
     * @Date: 2018/8/21
     */
    boolean republish(Task task);

    void cannel(int taskId);
}
