package com.kaituo.pms.controller;

import com.kaituo.pms.utils.MapUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

/**
 * @program: ktpms
 * @description: 可复用接口(错的)
 * @author: su
 * @create: 2018-07-25 09:44
 **/
@RestController
@RequestMapping(value = "utils")
public class UtilsController {
    @PostMapping("uploadImage")
    public Map<String , Object> fileUpload(@RequestParam("userphoto") CommonsMultipartFile file , String nickname , HttpServletRequest request) {
        // 获取文件名
        String fname = file.getOriginalFilename() ;

        // 新文件名
        String newFileName = UUID.randomUUID() + fname;

        // 获得项目的路径
        ServletContext sc = request.getServletContext();

        // 上传位置
        // 设定文件保存的目录
        String path = sc.getRealPath("/userphoto") + "/";

        File f = new File(path);
        if (!f.exists() || ! f.isDirectory())   {
            f.mkdirs();
        }
        if (!file.isEmpty()) {
            try {
                FileOutputStream fos = new FileOutputStream(path + newFileName);
                InputStream in = file.getInputStream();
                byte[] buffer = new byte[1024] ;
                int len = 0 ;
                while ((len = in.read(buffer, 0, buffer.length)) != -1) {
                    fos.write(buffer , 0 , len);
                }
                fos.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("上传图片到:" + path + newFileName);
        return MapUtil.setMap2("1","成功",null) ;
    }
}
