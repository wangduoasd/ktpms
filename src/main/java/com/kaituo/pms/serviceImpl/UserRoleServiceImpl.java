package com.kaituo.pms.serviceImpl;


import com.kaituo.pms.bean.Role;
import com.kaituo.pms.bean.UserRole;
import com.kaituo.pms.bean.UserRoleExample;

import com.kaituo.pms.dao.RoleMapper;
import com.kaituo.pms.dao.UserRoleMapper;

import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    RoleService roleService;
    @Override

    @Transactional(rollbackFor = Exception.class)
    public List<Role> findAllRole(int userId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (userRoles.isEmpty() ) {
            return null;
        } else {
/*            String[] roleNames = new String[7];
            int i = 0;*/
            List<Role> list=new ArrayList<>();
            for (UserRole userRole : userRoles) {

                list .add(roleService.getRoleById(userRole.getRoleId())) ;

            }
            return list;
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addRoles(List<String>  roleArray,int userId){
        UserRole userRole = new UserRole();

        for (String r:roleArray
             ) {
            userRole.setUserId(userId);
            userRole.setRoleId(Integer.parseInt(r));
            userRole.setInductionTime(new Date());
            userRoleMapper.insertSelective(userRole);

        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delUserRole(int userId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        return userRoleMapper.deleteByExample(userRoleExample);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int upUserRoles(List<String>  roleArray, Integer userId) {
        delUserRole(userId);
        return addRoles( roleArray,userId);

    }
}
