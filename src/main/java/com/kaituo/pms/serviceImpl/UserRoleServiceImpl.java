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
        System.out.println(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        System.out.println(userRoles);
        if (userRoles.isEmpty() ) {
            return null;
        } else {
            String[] roleNames = new String[7];
            int i = 0;
            for (UserRole userRole : userRoles) {
                roleNames[i] = "" + userRole.getRoleId();
                System.out.println("RoleId=" + userRole.getRoleId());
                i++;
            }
            return roleNames;
        }
    }
    @Override
    public int addRoles(String[] roleArray,int userId){
        UserRole userRole = new UserRole();
        for (String s:roleArray
             ) {
            userRole.setUserId(userId);
            userRole.setRoleId(Integer.parseInt(s));
            userRoleMapper.insertSelective(userRole);

        }
        return 1;
    }

    @Override
    public int delUserRole(int userId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        return userRoleMapper.deleteByExample(userRoleExample);
    }

    @Override
    public int upUserRoles(String[] roleArray, Integer userId) {
        delUserRole(userId);
        addRoles( roleArray,userId);
        return 0;
    }
}
