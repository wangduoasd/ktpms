package com.kaituo.pms.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Dept;
import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.TokenMap;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.scope.Scope;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: DeptController
 * @ProjectName pms
 * @Description:
 * @date 2018/8/21 002116:20
 */
@Slf4j
@Controller
@CrossOrigin
public class DeptController {
    @Autowired
    DeptService deptService;
    /*
     　  * @Description: 综服中心-员工设置-添加员工  获取所有部门列表
     　　* @param [dept, positionArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 15:54
     　　*/
    @GetMapping(value ="authority/one/depts/{token:.+}")
    @ResponseBody
    public OutJSON getAllDeptName(@PathVariable("token") String token) {
        try {
            Integer userId = TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            List<Dept> list = deptService.getAllDeptName();
            String newToken = TokenMap.remove(token, userId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, list,newToken);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /*
     * @Description: 综服中心-员工设置-添加员工  通过部门ID获取部门
     * @param [dept, positionArray]
     * @return com.kaituo.pms.utils.OutJSON
     * @throws
     * @author 张金行
     * @date 2018/8/23 0023 15:54
     */
    @GetMapping(value ="authority/four/dept/{deptId}/{token:.+}")
    @ResponseBody
    public OutJSON getDeptById(@PathVariable("deptId")int deptId,@PathVariable("token") String token){
        try {
            Integer userId = TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            Dept dept = deptService.getDeptById(deptId);
            if(dept==null){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
            }
            String newToken = TokenMap.remove(token, userId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, dept,newToken);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 风控中心_部门设置  部门列表
     　　* @param [dept, positionArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 15:54
     　　*/
    @GetMapping(value ="authority/four/depts/{pn}/{token:.+}")
    @ResponseBody
    public OutJSON findAllDept(@PathVariable(value = "pn") int pageNumber,
                               @RequestParam(value = "pageSize",defaultValue ="8") Integer pageSize,
                               @PathVariable("token") String token) {
        try {
            Integer userId = TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            PageHelper.startPage(pageNumber, pageSize);
            List<Dept> list = deptService.findAllDept();
            PageInfo pageInfo = new PageInfo(list, 5);
            String newToken = TokenMap.remove(token, userId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo,newToken);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 风控中心_部门设置  新建部门
     　　* @param [dept, positionArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 15:54
     　　*/
    @ResponseBody
    @PostMapping (value = "authority/four/dept/{token:.+}")
    public OutJSON addDept(Dept dept,@PathVariable("token") String token) {
        try {
            Integer userId = TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            int i= deptService.addDept(dept,dept.getPositionArray());
            if(i==1){
                String newToken = TokenMap.remove(token, userId);
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,null,newToken);}
            if(i==2)
                return OutJSON.getInstance(CodeAndMessageEnum.DEPT_ADD_ERROR);
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);

        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    @DeleteMapping("authority/four/dept/{deptId}/{token:.+}")
    @ResponseBody
    public OutJSON delDept(@PathVariable("deptId")int deptId,@PathVariable("token") String token) {
        try {
            Integer userId = TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            int i = deptService.delDept(deptId);
            if(i==1){
                String newToken = TokenMap.remove(token, userId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,null,newToken);}
            if(i==0){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);}
            String message="此部门还有"+(i-1)+"名员工，不能删除";
            return OutJSON.getInstance(CodeAndMessageEnum.DELETE_ERROR,message);
        }catch (Exception e){
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 风控中心_部门设置  修改部门
     　　* @param [dept, positionArray]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 15:54
     　　*/
    @PutMapping("authority/four/dept/{token:.+}")
    @ResponseBody
    public OutJSON upDept(Dept dept,@PathVariable("token") String token) {
        try {
            Integer userId = TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            int i = deptService.upDept(dept,dept.getPositionArray());
            if(i==1){
                String newToken = TokenMap.remove(token, userId);
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,null,newToken);}
            if(i==2){
                return OutJSON.getInstance(CodeAndMessageEnum.DEPT_ADD_ERROR);}
            if(i==3){
                return OutJSON.getInstance(CodeAndMessageEnum.DEPT_UP_ERROR);}
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);

        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
