package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_role;
import com.kaituo.pms.bean.p_roleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_roleMapper {
    int countByExample(p_roleExample example);

    int deleteByExample(p_roleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(p_role record);

    int insertSelective(p_role record);

    List<p_role> selectByExample(p_roleExample example);

    p_role selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") p_role record, @Param("example") p_roleExample example);

    int updateByExample(@Param("record") p_role record, @Param("example") p_roleExample example);

    int updateByPrimaryKeySelective(p_role record);

    int updateByPrimaryKey(p_role record);
}