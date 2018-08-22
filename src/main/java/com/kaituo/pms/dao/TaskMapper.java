package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.TaskExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TaskMapper {
    int countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    /**
     * 风控委——查询已发布任务
     * @Param:
     * @param
     * @return: java.util.List<com.kaituo.pms.bean.Task>
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    List<Task> listPublishedTask() ;

    /**
     * 风控委——查询已发布任务总数
     * @Param:
     * @param
     * @return: java.util.List<com.kaituo.pms.bean.Task>
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    int publishedTaskCount();

    /**
     * 风控委——查询已失效任务
     * @Param:
     * @param
     * @return: java.util.List<com.kaituo.pms.bean.Task>
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    List<Task> listInvalidTask();

    /**
     * 风控委——查询已发布任务总数
     * @Param:
     * @param
     * @return: java.util.List<com.kaituo.pms.bean.Task>
     * @Author: 苏泽华
     * @Date: 2018/8/20
     */
    int invalidTaskCount();
}