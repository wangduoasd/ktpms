package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_user;
import com.kaituo.pms.bean.p_userExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_userMapper {
    int countByExample(p_userExample example);

    int deleteByExample(p_userExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(p_user record);

    int insertSelective(p_user record);

    List<p_user> selectByExample(p_userExample example);

    p_user selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") p_user record, @Param("example") p_userExample example);

    int updateByExample(@Param("record") p_user record, @Param("example") p_userExample example);

    int updateByPrimaryKeySelective(p_user record);

    int updateByPrimaryKey(p_user record);
}