package com.kaituo.pms.service;

import com.kaituo.pms.bean.Role;
import com.kaituo.pms.bean.UserRole;

import java.util.List;

public interface UserRoleService {
    String[] findAllRole(int userId);
}
