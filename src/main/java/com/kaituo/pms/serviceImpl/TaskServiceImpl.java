package com.kaituo.pms.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.TaskExample;
import com.kaituo.pms.dao.TaskMapper;
import com.kaituo.pms.service.TaskService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: ktpms
 * @description: 有关任务的service
 * @author: 由苏泽华创建
 * @create: 2018-08-09 21:45
 **/

public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

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
        // 分页
        PageHelper.startPage(pageNamber, pageSize);
        // 条数
        int total = (int)countTaskByStatus(status);
        // 有数据就封装map返回上层
        if (0>total){
            List<Task> list = listTaskByStatus(status);
            Map<String , Object> pageMap = new HashMap<>(2);

            pageMap.put("total:" , total);
            pageMap.put("taskList" , list);

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , pageMap);
        }else {
            return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
        }
    }
}
