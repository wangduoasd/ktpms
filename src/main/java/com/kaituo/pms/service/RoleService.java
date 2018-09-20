package com.kaituo.pms.service;

import com.kaituo.pms.bean.Role;

import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: RoleService
 * @ProjectName pms
 * @Description:
 * @date 2018/8/28 00289:46
 */
public interface RoleService {
    List<Role> getAll();
    Role getRoleById(int roleId);
    List<Role> findRoleById(int userId);
    /**
     * @Description:
     * @Param:
     * @param roleId
     * @param userId
     * @return: boolean如果是true代表没有权限，false为有权限
     * @Author: 苏泽华,张金行
     * @Date: 2018/9/19
     */
    boolean checkRole(int roleId , int userId);
}
