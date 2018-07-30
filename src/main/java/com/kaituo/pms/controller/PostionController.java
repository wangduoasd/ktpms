package com.kaituo.pms.controller;

import com.kaituo.pms.domain.Position;
import com.kaituo.pms.service.PositionService;
import com.kaituo.pms.utils.MapUtil;
import com.kaituo.pms.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: ktpms
 * @description: 职位相关
 * @author: su
 * @create: 2018-07-24 21:06
 **/
@RestController
@RequestMapping("position")
public class PostionController {
    @Autowired
    PositionService positionService;

    /** 
    * @Description: 通过部门查找其下对应的职位
    * @Param:  deptId 部门ID
    * @return:  code:1 成功，code:0 失败
    * @Author: su
    * @Date: 2018/7/24 
    */ 
    @RequestMapping(value = "findPositionByDept" , method = RequestMethod.POST)
    public Map<String , Object> findPositionByDept(String deptId){
        List<Position> positionList = positionService.findPosition(deptId);
        if (null==positionList){
            return MapUtil.setMap2("0","失败,无数据",positionList);
        }
        return MapUtil.setMap2("1","成功",positionList);
    }
    /**
     * @Description: 风控中心-部门设置-新建部门按钮-添加按钮
     * @Param:
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/26
     */
    @RequestMapping(value = "addPostion",method = RequestMethod.POST)
    public Msg addPostion(Position position){
       int statu= positionService.addPostion(position);
       if(statu==1)
        return Msg.success();
       else
           return Msg.fail();
    }
}
