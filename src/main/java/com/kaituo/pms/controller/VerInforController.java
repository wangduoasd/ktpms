package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Version;
import com.kaituo.pms.service.VerinforService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.TokenMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class VerInforController {
    @Autowired
    VerinforService verinforService;
    /**
     * 查询版本信息
    * @Description: 个人中心 ，版本信息
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date:20188/10
    */
    @GetMapping(value="sys/versioninformaion/{token:.+}")
    public OutJSON findAllVerinfor(@PathVariable("token") String token){
        try {
            Integer userId=TokenMap.check(token);
            if(userId==null){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            Version allVerfor = verinforService.findAllVerfor();
            if (allVerfor != null) {
                String newToken=TokenMap.remove(token,userId);
                //版本信息获取成功
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, allVerfor,newToken);
            } else {
                //版本信息获取失败
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
            }
        }catch (Exception e){
           e.printStackTrace();
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);

        }
    }


}
