package com.kaituo.pms.controller;

import com.kaituo.pms.domain.Integral;
import com.kaituo.pms.domain.User;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: pms
 * @description: 有关积分的controller
 * @author: su
 * @create: 2018-07-29 21:08
 **/
@RestController
@RequestMapping("integral")
public class IntegralController {

    @Autowired
    IntegralService integralService;
    /** 
    * @Description: 个人中心积分记录（方案1）
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/29 
    */
    @PostMapping("findIntegralDetail")
    public Map<String , Object>findIntegralDetail(String pageNumber , String pageSize , String userID){
        Integer intPageNumber = Integer.parseInt(pageNumber);
        Integer intPageSize = Integer.parseInt(pageSize);
        Integer intUserID = Integer.parseInt(userID);
        return integralService.findIntegralDetail(intPageNumber , intPageSize , intUserID);
    }
    /**
     * @Description: 个人中心积分记录（方案2）
     * @Param:
     * @return:
     * @Author: su
     * @Date: 2018/7/29
     */
//    @PostMapping("findIntegralDetail")
//    public Map<String , Object>findIntegralDetail(String pageNumber , String pageSize , HttpServletRequest request){
//        Integer intPageNumber = Integer.parseInt(pageNumber);
//        Integer intPageSize = Integer.parseInt(pageSize);
//        HttpSession session = request.getSession();
//        User user = (User)session.getAttribute("user");
//        if(null==user){
//            return MapUtil.setMap2("1","获取员工id失败",null);
//        }
//        int userID = user.getUserId();
//        return integralService.findIntegralDetail(intPageNumber , intPageSize , userID);
//    }
}
