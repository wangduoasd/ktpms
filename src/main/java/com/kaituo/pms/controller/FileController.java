package com.kaituo.pms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableMap;
import com.kaituo.pms.bean.FileRecord;
import com.kaituo.pms.bean.FileUploadRecord;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.dao.FileRecordMapper;
import com.kaituo.pms.dao.FileUploadRecordMapper;
import com.kaituo.pms.previewModel.ReturnResponse;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/20 15:49
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    String fileDir= Util.getImgBasePath()+"\\";
    String demoDir = "file";
    String demoPath = demoDir + File.separator;
    @Autowired
    FileUploadRecordMapper fileUploadRecordMapper;
    @Autowired
    FileRecordMapper fileRecordMapper;
    @Autowired
    TokenService tokenService;
    @Autowired
    RoleService roleService;
    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file,String userName,
                             HttpServletRequest request,Integer status) throws JsonProcessingException {
        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
        }
        // 权限控制

        if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
            new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
        }
        String fileName = file.getOriginalFilename();
        File outFile = new File(fileDir + demoPath);
        if (!outFile.exists()) {
            outFile.mkdirs();
        }
        try(InputStream in = file.getInputStream();
            OutputStream ot = new FileOutputStream(fileDir + demoPath + fileName)){
            byte[] buffer = new byte[1024];
            int len;
            while ((-1 != (len = in.read(buffer)))) {
                ot.write(buffer, 0, len);
            }
            FileUploadRecord f=fileUploadRecordMapper.selectByFileName(fileName);
            //查询此文件是否在数据库中存在，不存在则加入，存在则按照上传人姓名做适当修改。
            //在服务器存储则名称相同为替换，不存在副本
            if (f==null){
                FileUploadRecord fileUploadRecord = new FileUploadRecord(fileName, userName,status);
                fileUploadRecordMapper.insertFileRecord(fileUploadRecord);
            }else{
                fileUploadRecordMapper.updateUserNameByFileName(userName,fileName);
            }
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "SUCCESS", null));
        } catch (IOException e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(-1, "FAILURE", null));
        }
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "deleteFile", method = RequestMethod.GET)
    public String deleteFile(String fileName) throws JsonProcessingException {
        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
        }
        // 权限控制

        if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
            new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
        }
        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        File file = new File(fileDir + demoPath + fileName);
        if (file.exists()) {
            file.delete();
        }
        //删除浏览及下载记录
        FileUploadRecord fileUploadRecords=fileUploadRecordMapper.selectByFileName(fileName);
        fileRecordMapper.deleteByFileId(fileUploadRecords.getId());
        //删除上传记录
        fileUploadRecordMapper.deleteFileRecord(fileName);

        return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "SUCCESS", null));
    }

    /**
     * 获取全部上传文件列表
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "listFiles", method = RequestMethod.GET)
    public String getFiles(@RequestParam Integer status,@RequestParam Integer pageNO,@RequestParam String  fileName) throws JsonProcessingException {
        System.out.println(pageNO);
        //从文件夹中获取文件列表
//        List<Map<String, String>> list = Lists.newArrayList();
//        File file = new File(fileDir + demoPath);
//        if (file.exists()) {
//            Arrays.stream(file.listFiles()).forEach(file1 -> list.add(ImmutableMap.of("fileName", demoDir + "/" + file1.getName())));
//        }
        PageHelper.startPage(pageNO,10);
        List<FileUploadRecord> list=fileUploadRecordMapper.selectAllRecord(status,fileName);
        for (FileUploadRecord f:list
             ) {
            List<FileRecord> lt=fileRecordMapper.selectByFileId(f.getId());
            f.setFileRecords(lt);
        }
        PageInfo page = new PageInfo(list);
        return new ObjectMapper().writeValueAsString(page);
    }

    /**
     * 下载服务器文件
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(HttpServletRequest request, HttpServletResponse response, String fileName,
                           String userName) throws JsonProcessingException {

        try {
            boolean message= new UploadFile().download(request,response,fileName);
            FileUploadRecord fileUploadRecords=fileUploadRecordMapper.selectByFileName(fileName);
            fileUploadRecordMapper.updateDownload(fileUploadRecords.getId());
            FileRecord fileRecord=new FileRecord(fileUploadRecords.getId(),null,userName);
            fileRecordMapper.insertRecord(fileRecord);
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "SUCCESS", null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(-1, "FAILURE", null));
        }
    }

    /**
     * 增加文件浏览次数及浏览记录
     * @param request
     * @param response
     * @param fileName
     * @param userName
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/reading", method = RequestMethod.GET)
    public String reading(HttpServletRequest request, HttpServletResponse response, String fileName,
                           String userName) throws JsonProcessingException {
        try {
            FileUploadRecord fileUploadRecords=fileUploadRecordMapper.selectByFileName(fileName);
            if(fileUploadRecords!=null&&userName!=null){
                fileUploadRecordMapper.updateReading(fileUploadRecords.getId());
                FileRecord fileRecord=new FileRecord(fileUploadRecords.getId(),userName,null);
                fileRecordMapper.insertRecord(fileRecord);
                return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "SUCCESS", null));
            }else{
                return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(-1, "FAILURE", null));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(-1, "FAILURE", null));
        }
    }
    /**
     * 获取文件浏览记录
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "listFileRecord", method = RequestMethod.POST)
    public String listFileRecord() throws JsonProcessingException {
        List<FileRecord> list=fileRecordMapper.selectAllFileRecord();
        return new ObjectMapper().writeValueAsString(list);
    }
//    /**
//     * 获取文件浏览次数
//     * @return
//     * @throws JsonProcessingException
//     */
//    @RequestMapping(value = "listFileRecordCount", method = RequestMethod.POST)
//    public String listFileRecordCount() throws JsonProcessingException {
//        List<FileUploadRecord> list=fileUploadRecordMapper.selectAllRecord();
//        return new ObjectMapper().writeValueAsString(list);
//    }

//    /**
//     * 删除服务器文件（积分考勤表）及数据库记录
//     * @param fileName
//     * @return
//     * @throws IOException
//     */
//    @PostMapping("/deleteFile")
//    public OutJSON deleteFile(String fileName)  {
//        try {
//            String token = ContextHolderUtils.getRequest().getHeader("token");
//            // 检查token并获得userID
//            Token token1 = tokenService.selectUserIdByToken(token);
//            if (null == token1){
//                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
//            }
//            // 权限控制
//
//            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
//                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
//            }
//            boolean flag=attendacneService.downFile(fileName);
//            if(flag){
//                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,flag);
//            }else {
//                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR,flag);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
//        }
//    }
}
