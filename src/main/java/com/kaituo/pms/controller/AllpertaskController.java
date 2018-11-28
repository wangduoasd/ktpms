package com.kaituo.pms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.DTO.AllpertaskDTO;
import com.kaituo.pms.DTO.AllpertaskDTO1;
import com.kaituo.pms.DTO.GetalltaskperDTO;
import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.error.MyException;
import com.kaituo.pms.service.AllpertaskService;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    /**
     *发布任务
     * @param file 文件路径
     * @param allpertask
     * @param start 开始时间
     * @param end 结束时间
     * @return id
     * @throws ParseException
     */

    @PostMapping("authority/five/tasks/manage/statusAll")
    public OutPut distributeAllTask(MultipartFile file,Allpertask allpertask,String start,String end) throws ParseException{
        try {
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
                    allpertask.setAllpertask_image ("/image/kaituo.png");
                // 如果是1则为上传成功
                case Constant.IMG_UPLOSD_SUCCESS:
                    // 获取相对路径
                    String url = (String) map.get ("url");
                    url = url.replace (Util.seperator, "/");
                    if(url==null||url.equals ("")){
                        url="/image/kaituo.png";
                    }
                    allpertask.setAllpertask_image (url);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startat =  sdf.parse(start);;
            Date endat=sdf.parse (end);
            allpertask.setAllpertask_starttime (startat);
            allpertask.setAllpertask_endtime (endat);
            int id=allpertaskService.distribute_Allpertask(allpertask);
            return ResultUtil.success (id);
        } catch (MyException e) {
            return ResultUtil.error (e.getCode (), e.getMessage ());
        }

    }

    /**
     * 删除任务（备用，可删除已过期且无人领的任务关系p_allpertask_user）
     * @param Allpertask_id
     * @return
     */
    @PutMapping("authority/five/tasks/manage/deleteAll")
    public OutPut deleteAllTask(@RequestParam("Allpertask_id") Integer Allpertask_id){
        try {
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
            allpertaskService.delete_Allpertask (Allpertask_id);
            return ResultUtil.success ();
        } catch (MyException e) {
            return ResultUtil.error (e.getCode (), e.getMessage ());
        }

    }

    /**
     * 任务列表（管理员）
     * @param pn
     * @return
     */
   @GetMapping(value = "authority/five/tasks/manage/select/{pn}")
    public OutPut AllpertaskList(@PathVariable(value="pn")Integer pn){
        //引入PageHelper分页插件
        //查询只需要调用,传入的页码，以及每页的大小
        PageHelper.startPage(pn,10);
        //startpage后面紧的这个查询就是一个分页查询
       List<AllpertaskDTO1> allpertaskDTOList= null;
       try {
           allpertaskDTOList = allpertaskService.find_Allpertask_ofadmin ();
       } catch (MyException e) {
           return ResultUtil.error(e.getCode (), e.getMessage ());
       }
       //使用pageinfo包装查询后的结果，只需要将pageinfo交给页面就行了
        //封装了详细的分页信息，包括我们查询出来的数据,传入连续显示的页数。
        PageInfo page = new PageInfo(allpertaskDTOList);
        return ResultUtil.success(page);
    }

    /**
     * 审核列表
     * @param pn
     * @return
     */
    @GetMapping(value = "authority/five/tasks/manage/checkselect/{pn}")
    public OutPut CheckAllpertaskList(@PathVariable(value="pn")Integer pn){
        //引入PageHelper分页插件
        //查询只需要调用,传入的页码，以及每页的大小


        //startpage后面紧的这个查询就是一个分页查询
        try {
            PageHelper.startPage(pn,10);
            List<AllpertaskDTO1>  allpertaskDTOList = allpertaskService.find_allpertaskfinish();
            PageInfo page = new PageInfo(allpertaskDTOList,5);
            return ResultUtil.success(page);
        } catch (MyException e) {
            return ResultUtil.error(e.getCode (), e.getMessage ());
        }



    }

    /**
     * 审核通过
     * @param allpertask_id
     * @return
     */
    @PostMapping(value = "authority/five/tasks/manage/checkpass")
    public OutPut Check_Pass(@RequestParam("allpertask_id")int allpertask_id){
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            int userid= token1.getUserId ();
            if (null == token1){
                return  OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            allpertaskService.pass_allpertask (allpertask_id,userid);
            return ResultUtil.success ();
        } catch (MyException e) {
            return ResultUtil.error (e.getCode (), e.getMessage ());
        }
    }

    /**
     * 审核未通过
     * @param allpertask_id
     * @return
     * @throws InterruptedException
     */
    @PutMapping(value = "authority/five/tasks/manage/checkfail")
    public OutPut Check_Fail(@RequestParam("allpertask_id")int allpertask_id) throws InterruptedException {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            int userid= token1.getUserId ();
            if (null == token1){
                return  OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            String message=allpertaskService.fail_allpertask (allpertask_id,userid);
            return ResultUtil.success (message);
        } catch (MyException e) {
            return ResultUtil.error (e.getCode (), e.getMessage ());
        }


    }

    /**
     * 我的任务列表(进行中1，审核中2，已完成3)
     * @param pn
     * @return
     */
    @GetMapping(value = "authority/five/tasks/employee/select/{pn}/{status}/{token}")
    public OutPut AllpertaskListstaff(@PathVariable(value="pn")int pn,
                                      @PathVariable(value="status")int status,
                                      @PathVariable(value="token")String token
    ){
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        if (null == token1.getUserId()){
            return OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }

        int userId = token1.getUserId();
        //引入PageHelper分页插件
        //查询只需要调用,传入的页码，以及每页的大小

        //startpage后面紧的这个查询就是一个分页查询
        PageInfo allpertaskDTOList= null;
        try {
            allpertaskDTOList = allpertaskService.find_Allpertask_ofuser (userId,status,pn);
        } catch (MyException e) {
            return ResultUtil.error(e.getCode (), e.getMessage ());
        }
        //使用pageinfo包装查询后的结果，只需要将pageinfo交给页面就行了

        return ResultUtil.success(allpertaskDTOList);
    }

    /**
     * 任务大厅
     * @param pn
     * @return
     */
    @GetMapping(value = "authority/five/tasks/select/{pn}")
    public OutPut AllstafftaskList(@PathVariable("pn")int pn){
        //引入PageHelper分页插件
        //查询只需要调用,传入的页码，以及每页的大小

        //startpage后面紧的这个查询就是一个分页查询

        try {
            //PageHelper.startPage (pn,10);
//            allpertaskService.AllpertaskList (pn,10);
//            System.out.println ("--------------------------------------------------------------------------");
//            System.out.println (allpertaskDTOList);
//            PageInfo page = new PageInfo(allpertaskDTOList);
//            page.setTotal (allpertaskDTOList.size ());
            return ResultUtil.success( allpertaskService.AllpertaskList (pn,10));
        } catch (MyException e) {
            return ResultUtil.error(e.getCode(), e.getMessage ());
        }
    }

    /**
     *领取任务
     * @param allpertask_id
     * @return
     * @throws Exception
     */
    @PostMapping(value = "authority/five/tasks/employee/get")
    public OutPut getAllpertask(@RequestParam("allpertask_id")int allpertask_id) throws Exception {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
           int userId= token1.getUserId ();
            if (null == token1){
                return  OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            String message=allpertaskService.get_Allpertask (allpertask_id,userId);
            if(message!=null){
                return ResultUtil.error ("99",message);
            }
            return ResultUtil.success (message);
        } catch (MyException e) {
            return ResultUtil.error (e.getCode (), e.getMessage ());
        }
    }

    /**
     *取消任务
     * @param allpertask_id
     * @return
     */
    @PutMapping(value = "authority/five/tasks/employee/giveup")
    public OutPut giveUpAllpertask(@RequestParam("allpertask_id")int allpertask_id) {
        try {
            String token = ContextHolderUtils.getRequest ().getHeader ("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken (token);
            int userId = token1.getUserId ();
            if (null == token1) {
                return OutPut.getInstance (CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制
            allpertaskService.giveup_allpertask (allpertask_id, userId);
            return ResultUtil.success ();
        } catch (MyException e) {
            return ResultUtil.error (e.getCode (), e.getMessage ());
        }
    }
        /**
         *完成任务
         * @param allpertask_id
         * @return
         */
    @PutMapping(value = "authority/five/tasks/employee/finish")
     public OutPut finishAllpertask(@RequestParam("allpertask_id")int allpertask_id) {
            try {
                String token =ContextHolderUtils.getRequest().getHeader("token");
                // 检查token并获得userID
                Token token1 = tokenService.selectUserIdByToken(token);
                int userId= token1.getUserId ();
                if (null == token1){
                    return  OutPut.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
                }
                // 权限控制
                allpertaskService.finish_allpertask (allpertask_id,userId);
                return ResultUtil.success ();
            } catch (MyException e) {
                return ResultUtil.error (e.getCode (), e.getMessage ());
            }
    }

    /**
     * 领取人
     * @param allpertask_id
     * @return
     */
    @GetMapping(value = "authority/five/tasks/select/getper/{allpertask_id}")
    public OutPut getper(@PathVariable("allpertask_id")int allpertask_id){
        //引入PageHelper分页插件
        //查询只需要调用,传入的页码，以及每页的大小
        //startpage后面紧的这个查询就是一个分页查询
        try {
           List<GetalltaskperDTO> getalltaskperDTOList= allpertaskService.findpertest (allpertask_id);
            System.out.println (getalltaskperDTOList);
            return ResultUtil.success(getalltaskperDTOList);
        } catch (MyException e) {
            return ResultUtil.error(e.getCode(), e.getMessage ());
        }
    }
}
