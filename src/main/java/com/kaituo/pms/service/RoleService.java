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
}
