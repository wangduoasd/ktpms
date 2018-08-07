package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_permission;
import com.kaituo.pms.bean.p_permissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_permissionMapper {
    int countByExample(p_permissionExample example);

    int deleteByExample(p_permissionExample example);

    int deleteByPrimaryKey(Integer permissionId);

    int insert(p_permission record);

    int insertSelective(p_permission record);

    List<p_permission> selectByExample(p_permissionExample example);

    p_permission selectByPrimaryKey(Integer permissionId);

    int updateByExampleSelective(@Param("record") p_permission record, @Param("example") p_permissionExample example);

    int updateByExample(@Param("record") p_permission record, @Param("example") p_permissionExample example);

    int updateByPrimaryKeySelective(p_permission record);

    int updateByPrimaryKey(p_permission record);
}