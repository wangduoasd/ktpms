package com.kaituo.pms.service;

/**
 * 查询版本信息
 * @Description: 个人中心版本信息
 * @Param:
 * @return:
 * @Author: 侯鹏
 * @Date:2018/8/18
 */
import com.kaituo.pms.bean.Version;
import com.kaituo.pms.dao.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface VerinforService {

   Version findAllVerfor();

}
