package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.service.UserRoleService;
import com.kaituo.pms.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: UserRoleController
 * @ProjectName pms
 * @Description:
 * @date 2018/8/23 002312:41
 */
@Controller
@Slf4j
@CrossOrigin
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    TokenService tokenService;
    @Autowired
    RoleService roleService;
    /**
     　  * @Description: 权限管理_添加员工_确认按钮  添加权限
     　　* @param [user, roleArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 14:08
     　　*/
    @ResponseBody
    @PostMapping(value = "authority/all/roles")
    public OutJSON addUserRole(User user) {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_ALL,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }

            if(user.getUserId()==null||user.getRoles()==null||user.getRoles().size()==0){
                return OutJSON.getInstance(CodeAndMessageEnum.FIND_RANKING_BY_PAGE_NULL);
            }
            int i=userRoleService.addRoles(user.getRoles(),user.getUserId());

            if(i==1){

                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,null);}
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 权限管理_权限修改
     　　* @param [user, roleArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 14:27
     　　*/
    @ResponseBody
    @PutMapping(value = "authority/all/roles")
    public OutJSON upUserRole(User user) {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_ALL,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            if(user.getUserId()==null||user.getRoles()==null||user.getRoles().size()==0){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_AUTH_ADD_ERROR);
            }
            int i=userRoleService.upUserRoles(user.getRoles(),user.getUserId());
            if(i==1){

                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);}
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 权限管理_删除  仅删除权限 不删除员工
     　　* @param [user, roleArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 14:08
     　　*/
    @ResponseBody
    @DeleteMapping(value = "authority/all/role/{userId}/{token:.+}")
    public OutJSON addUserRole(@PathVariable("userId") int userId,@PathVariable("token")String token) {
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
            int i=userRoleService.delUserRole(userId);
            if(i>=1){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);}
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
