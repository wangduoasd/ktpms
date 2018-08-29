package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Role;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class RoleController {
    @Autowired
    RoleService roleService;
    @ResponseBody
    @GetMapping(value = "roles")
    public OutJSON getExchangeRecords() {
        try {
            List<Role> list = roleService.getAll();
            if(list.size()==0){
                return OutJSON.getInstance(CodeAndMessageEnum.ROLE_EMPTY);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, list);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
