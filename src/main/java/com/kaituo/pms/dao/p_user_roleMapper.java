package com.kaituo.pms.dao;

import com.kaituo.pms.bean.p_user_role;
import com.kaituo.pms.bean.p_user_roleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface p_user_roleMapper {
    int countByExample(p_user_roleExample example);

    int deleteByExample(p_user_roleExample example);

    int insert(p_user_role record);

    int insertSelective(p_user_role record);

    List<p_user_role> selectByExample(p_user_roleExample example);

    int updateByExampleSelective(@Param("record") p_user_role record, @Param("example") p_user_roleExample example);

    int updateByExample(@Param("record") p_user_role record, @Param("example") p_user_roleExample example);
}