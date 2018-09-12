package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Role;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.JwtToken;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.TokenMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: RoleController
 * @ProjectName pms
 * @Description:
 * @date 2018/8/28 00289:51
 */
@Controller
@Slf4j
@CrossOrigin
public class RoleController {
    @Autowired
    RoleService roleService;
    /**
     　  * @Description: 权限管理_添加员工_权限列表
     　　* @param [user, roleArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 14:08
     　　*/
    @ResponseBody
    @GetMapping(value = "authority/all/roles/{token:.+}")
    public OutJSON getAll(@PathVariable("token") String token) {
        try {
            Integer userId = TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            List<Role> list = roleService.getAll();
            if(list.size()==0||list==null){
                return OutJSON.getInstance(CodeAndMessageEnum.ROLE_EMPTY);
            }
            String newToken = TokenMap.remove(token, userId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, list,newToken);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 权限管理_添加员工_权限列表 获得相应用户的权限列表
     　　* @param [user, roleArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 14:08
     　　*/
    @ResponseBody

    @GetMapping(value = "authority/all/roles/{userId}/{token:.+}")
    public OutJSON getRolesById(@PathVariable("userId")int userId,@PathVariable("token") String token) {
        try {
            Integer managerId = TokenMap.check(token);
            if(managerId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            List<Role> list = roleService.findRoleById(userId);
            if(list.size()==0||list==null){
                return OutJSON.getInstance(CodeAndMessageEnum.ROLE_EMPTY);
            }
            String newToken = TokenMap.remove(token, managerId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,list,newToken);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
