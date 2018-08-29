package com.kaituo.pms.service;

import com.kaituo.pms.bean.Role;
import com.kaituo.pms.bean.UserRole;

import java.util.List;

public interface UserRoleService {
    /**
     　  * @Description: 查找该用户权限
     　　* @param
     　　* @return
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 14:31
     　　*/
    List<Role> findAllRole(int userId);
    /**
     　  * @Description: 添加用户权限
     　　* @param
     　　* @return
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 14:31
     　　*/
    int addRoles(String[] roleArray,int userId);
     /**
        　  * @Description: 删除用户权限
        　　* @param
        　　* @return
        　　* @throws
        　　* @author 张金行
        　　* @date 2018/8/23 0023 14:31
        　　*/
    int delUserRole(int userId);
     /**
        　  * @Description:修改用户权限
        　　* @param
        　　* @return
        　　* @throws
        　　* @author 张金行
        　　* @date 2018/8/23 0023 14:31
        　　*/
    int upUserRoles(String[] roleArray, Integer userId);
}
