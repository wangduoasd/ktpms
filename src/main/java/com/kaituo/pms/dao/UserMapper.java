package com.kaituo.pms.dao;

import com.kaituo.pms.bean.User;
import com.kaituo.pms.bean.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 从用户视图中获取除超级管理员外全部数据
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/8
     */
    List<User> selectUsersByView();

    /**
     * 从用户视图中获取除超级管理员外全部数据的条数
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/8
     */
    long countUsersByView();

    /** 
    * @Description: 按检索条件查询积分排行
    * @Param:  
    * @return:  
    * @Author: 苏泽华
    * @Date: 2018/8/9 
    */ 
    List<User>selectUsersByViewAndCondition(String condition);

    /**
     * @Description: 按检索条件查询积分排行的数据条数
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    long countUsersByViewAndCondition(String condition);

    User findPersonalDetail(@Param("userId") int id);
}