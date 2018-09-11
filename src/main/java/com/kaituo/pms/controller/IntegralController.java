package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Integral;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.JwtToken;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.TokenMap;
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
        PageHelper.startPage(ageNumber,pageSize);
        try {
            Integer userId=TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            Map<String , Object> map = new HashMap<>();
            List<Map<String, Object>> integrals = integralService.listIntegeral(userId);
          PageInfo pageInfo = new PageInfo(integrals, 5);
            if(integrals!=null&&integrals.size()>0){
                String newToken=TokenMap.remove(token,userId);
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,pageInfo,newToken);

            }
        } catch (Exception e) {            e.printStackTrace();

            log.error(e.getMessage());
        }
        return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }
}
