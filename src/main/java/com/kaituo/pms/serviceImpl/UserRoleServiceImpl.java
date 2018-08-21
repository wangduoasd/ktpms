package com.kaituo.pms.serviceImpl;


import com.kaituo.pms.bean.UserRole;
import com.kaituo.pms.bean.UserRoleExample;

import com.kaituo.pms.dao.UserRoleMapper;

import com.kaituo.pms.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: UserRoleImpl
 * @ProjectName pms
 * @Description:
 * @date 2018/8/20 002016:32
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String[] findAllRole(int userId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        String[] roleNames=new String[7];
        int i=0;
        for(UserRole userRole:userRoles){
            roleNames[i]=""+userRole.getRoleId();
            System.out.println("RoleId="+userRole.getRoleId());
            i++;
        }
        return  roleNames;
    }
}
