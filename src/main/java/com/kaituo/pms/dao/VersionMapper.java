package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Version;
import com.kaituo.pms.bean.VersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface VersionMapper {
    int countByExample(VersionExample example);

    int deleteByExample(VersionExample example);

    int deleteByPrimaryKey(Integer versionId);

    int insert(Version record);

    int insertSelective(Version record);

    List<Version> selectByExample(VersionExample example);

    Version selectByPrimaryKey(Integer versionId);

    int updateByExampleSelective(@Param("record") Version record, @Param("example") VersionExample example);

    int updateByExample(@Param("record") Version record, @Param("example") VersionExample example);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);
    /**
    * @Description:个人中心，版本细信息
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date:  2018/8/10
    */
    Version selectAllVersionInfor();
}