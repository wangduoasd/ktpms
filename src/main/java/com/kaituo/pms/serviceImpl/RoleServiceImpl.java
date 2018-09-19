package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Role;
import com.kaituo.pms.bean.RoleExample;
import com.kaituo.pms.dao.RoleMapper;
import com.kaituo.pms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: RoleServiceImpl
 * @ProjectName pms
 * @Description:
 * @date 2018/8/28 00289:47
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> getAll() {
        RoleExample roleExample = new RoleExample();

        return roleMapper.selectByExample(roleExample);
    }
    @Override
    public Role getRoleById(int roleId) {

        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> findRoleById(int userId) {
        return roleMapper.findRoleById(userId);
    }

    @Override
    public boolean checkRole(int roleId , int userId) {
        // 权限控制
        List<Role> roleById = findRoleById(userId);
        boolean falg = true;
        for(Role r:roleById){
            if (roleId==r.getRoleId()) {
                falg = false;
            }
        }
        return falg;
    }
}
