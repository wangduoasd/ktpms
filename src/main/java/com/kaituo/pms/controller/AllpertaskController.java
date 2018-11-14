package com.kaituo.pms.controller;

import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.Task;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.error.MyException;
import com.kaituo.pms.service.AllpertaskService;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Map;

import static com.kaituo.pms.utils.OutJSON.getInstance;

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
    @Autowired
    TokenService tokenService;
    @Autowired
    RoleService roleService;
    @PostMapping("authority/five/tasks/statusAll")
    public OutPut distributeAllTask(MultipartFile file,Allpertask allpertask,String start,String end){
        try {
            int id=allpertaskService.distribute_Allpertask(allpertask);
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return  OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 图片上传并获取上传的状态
            Map<String, Object> map = Util.imgUpload(file , Util.getImgRelativePath());

            // 上传的状态码
            int key = (int)map.get("code");
            switch (key) {

                // 如果是零
                case Constant.IMG_UPLOSD_ERROR:
                    // 返回图片上传失败
                    return OutPut.getInstance (CodeAndMessageEnum.PUBLISHING_TASK_IMAGE_HAS_BEEN_SUCCESSFULLY_UPLOADED);
                // 如果是2
                case Constant.IMG_UPLOSD_EMPTY:
                    // 返回图片为空
                    return OutPut.getInstance (CodeAndMessageEnum.PUBLISHING_TASK_IMAGE_IS_EMPTY);
                // 如果是1则为上传成功
                case Constant.IMG_UPLOSD_SUCCESS:
                    // 获取相对路径
                    String url = (String) map.get ("url");
                    url = url.replace (Util.seperator, "/");
            }
//            SimpleDateFormat formatter = new SimpleDateFormat ("ss mm HH dd MM  yyyy");
//            String endCron = formatter.format (totime);
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
