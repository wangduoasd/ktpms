package com.kaituo.pms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableMap;
import com.kaituo.pms.previewModel.ReturnResponse;
import com.kaituo.pms.utils.*;
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
    @Value("${file.dir}")
    String fileDir;
    String demoDir = "file";
    String demoPath = demoDir + File.separator;

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request) throws JsonProcessingException {
        String fileName = file.getOriginalFilename();
        // 判断该文件类型是否有上传过，如果上传过则提示不允许再次上传
//        if (existsTypeFile(fileName)) {
//            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "每一种类型只可以上传一个文件，请先删除原有文件再次上传", null));
//        }
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
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "SUCCESS", null));
        } catch (IOException e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "FAILURE", null));
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
        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        File file = new File(fileDir + demoPath + fileName);
        if (file.exists()) {
            file.delete();
        }
        return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "SUCCESS", null));
    }

    /**
     * 获取文件夹全部文件列表
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "listFiles", method = RequestMethod.GET)
    public String getFiles() throws JsonProcessingException {
        List<Map<String, String>> list = Lists.newArrayList();
        File file = new File(fileDir + demoPath);
        if (file.exists()) {
            Arrays.stream(file.listFiles()).forEach(file1 -> list.add(ImmutableMap.of("fileName", demoDir + "/" + file1.getName())));
        }
        return new ObjectMapper().writeValueAsString(list);
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
    public OutJSON download(HttpServletRequest request, HttpServletResponse response, String fileName)  {
        try {
            boolean message= new UploadFile().download(request,response,fileName);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,message);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
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
