package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Role;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.Constant;
import com.kaituo.pms.utils.JwtToken;
import com.kaituo.pms.utils.OutJSON;
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
    @Autowired
    TokenService tokenService;
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
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_ALL,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            List<Role> list = roleService.getAll();
            if(list.size()==0||list==null){
                return OutJSON.getInstance(CodeAndMessageEnum.ROLE_EMPTY);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, list);
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
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_ALL,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            List<Role> list = roleService.findRoleById(userId);
            if(list.size()==0||list==null){
                return OutJSON.getInstance(CodeAndMessageEnum.ROLE_EMPTY);
            }

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,list);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
