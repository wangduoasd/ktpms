package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.ChangeIntegral;
import com.kaituo.pms.bean.Integral;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

@Slf4j
@RestController
@CrossOrigin
public class IntegralController {
    @Autowired
    IntegralService integralService;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;


    /**
     * 积分明细
     * @Description: 积分明细
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date:2018/8/18
     */
    @GetMapping(value="integral/{token:.+}/{pageNumber}")
    public OutJSON findIntegral(@PathVariable("token") String token, @PathVariable("pageNumber")  int ageNumber,
                                @RequestParam (value = "pageSize",defaultValue = "10") int pageSize){

        try {
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            PageHelper.startPage(ageNumber,pageSize);
          /*  List<Map<String, Object>> integrals = integralService.listIntegeral(token1.getUserId());*/
            List<Integral> integrals = integralService.selectIntegralById(token1.getUserId());
            PageInfo pageInfo = new PageInfo(integrals, 5);
            if(integrals!=null){

                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,pageInfo);

            }
        } catch (Exception e) {

            log.error(e.getMessage());
        }
        return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }

    /**
     * 导入Excel来变动积分
     */
    @PostMapping("changeIntegralByExcel")
    @ResponseBody
    public OutPut changeIntegralByExcel(@RequestBody List<Object> list,@RequestBody String token){
        OutPut outPut = new OutPut();
        Token token1 = tokenService.selectUserIdByToken(token);
        Integer userId = token1.getUserId();
        if (null == token1){
            outPut.setMessage("请登录");
            outPut.setCode("0");
            return outPut;
        }
        if(list == null || list.size() == 0){
            outPut.setMessage(CommonEnum.SUCCESS.getName());
            outPut.setCode(CommonEnum.SUCCESS.getCode());
            return outPut;
        }
        try {
            for(int i=0;0<list.size();i++){
                ChangeIntegral ci = (ChangeIntegral) list.get(i);

                //修改用户明细积分
                integralService.updateByUserId(ci,userId);

                //修改用户表积分
                userService.updateIntegralByUserId(ci);
            }
            outPut.setMessage(CommonEnum.SUCCESS.getName());
            outPut.setCode(CommonEnum.SUCCESS.getCode());
            return outPut;
        }catch (Exception e){
            outPut.setMessage(CommonEnum.ERROR.getName());
            outPut.setCode(CommonEnum.ERROR.getCode());
            return outPut;
        }
    }
}
