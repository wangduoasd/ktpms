package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.Task;
import com.kaituo.pms.error.MyException;
import com.kaituo.pms.service.AllpertaskService;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.OutPut;
import com.kaituo.pms.utils.ResultUtil;
import com.kaituo.pms.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author wangduo
 * @date 2018/11/12 - 14:53
 */
@Slf4j
@RestController
@CrossOrigin
public class AllpertaskController {
    @Autowired
    AllpertaskService allpertaskService;
    @PostMapping("authority/five/tasks/statusAll")
    public OutPut distributeAllTask(MultipartFile file,Allpertask allpertask){
        try {
            int id=allpertaskService.distribute_Allpertask(allpertask);
            Map<String, Object> map = Util.imgUpload(file , Util.getImgRelativePath());
            return ResultUtil.success (id);
        } catch (MyException e) {
            return ResultUtil.error (e.getCode (), e.getMessage ());
        }

    }
    @PutMapping("authority/five/tasks/deleteAll")
    public OutPut deleteAllTask(Integer Allpertask_id){
        try {
            allpertaskService.delete_Allpertask (Allpertask_id);
            return ResultUtil.success ();
        } catch (MyException e) {
            return ResultUtil.error (e.getCode (), e.getMessage ());
        }
    }


}
