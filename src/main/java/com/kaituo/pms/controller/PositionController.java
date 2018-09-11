package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Position;
import com.kaituo.pms.service.PositionService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.TokenMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: PositionController
 * @ProjectName pms
 * @Description:
 * @date 2018/8/22 002212:45
 */
@Slf4j
@Controller
@CrossOrigin
public class PositionController {
    @Autowired
    PositionService positionService;
    /**
     　  * @Description: 综服中心_员工设置_添加新员工 点击部门后获取职位列表
     　　* @param [deptId]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 16:02
     　　*/
    @ResponseBody
    @GetMapping(value = "authority/one/positions/{deptId}/{token:.+}")
    public OutJSON findAllDept(@PathVariable(value = "deptId") int deptId,@PathVariable("token") String token) {
        try {
            Integer userId = TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            List<Position> list = positionService.getPositionNameBydeptId(deptId);
            if(list.size()==0||list==null)
                return OutJSON.getInstance(CodeAndMessageEnum.POSITION_FIND_ERROR);
            String newToken = TokenMap.remove(token, userId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, list,newToken);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
