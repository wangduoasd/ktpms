package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Version;
import com.kaituo.pms.dao.VersionMapper;
import com.kaituo.pms.service.VerinforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 查询版本信息
 * @Description: 个人中心版本信息
 * @Param:
 * @return:
 * @Author: 侯鹏
 * @Date:2018/8/18
 */
@Service
public class VerinforServiceImpl implements VerinforService {
    @Autowired
    VersionMapper versionMapper;
    @Override
    @Transactional
    public Version findAllVerfor() {
        Version version = versionMapper.selectAllVersionInfor();
        return version;
    }
}
