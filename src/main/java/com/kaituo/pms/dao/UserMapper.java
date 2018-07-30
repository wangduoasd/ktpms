package com.kaituo.pms.dao;

import com.kaituo.pms.domain.User;
import com.kaituo.pms.domain.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    long countByExample(UserExample example);

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

    long selectNumberOfUserByCondition(String condition);

    /**
    * @Description: 自定义的用于根据条件查询SQL
    * @Param:
    * @return:
    * @Author: su
    * @Date: 2018/7/27
    */
    List<User> selectByCondition(String condition);
    List<User> selectBykeyWord(@Param("keyword")String keyword);
    User findByUserName(@Param("username")String username);
}