package com.kaituo.pms.service;

import com.kaituo.pms.bean.Attendance;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttendacneService {

    boolean uploadExcel(String fileName, MultipartFile file) throws IOException;

    Attendance selectById(int i);

    List<Attendance> selectAll();

    void updateByExample(Attendance attendance);

    List<Attendance> selectByExample();
    /**
     * 上传Excel，修改积分
     * @param fileName
     * @param file
     */
    List<Object> uploadExcelToIntergral(String fileName, MultipartFile file) throws Exception;
    /**
     * 上传计算后的积分
     */
    void calculationOfIntegral(List<Attendance> attendances);

    /**
     * 上传文件并同步信息到数据库
     * @param file
     * @param username
     */
    void uploadFile( MultipartFile file,String username);

    /**
     * 删除服务器文件（积分考勤表）及数据库记录
     * @param filename
     * @return
     */
    boolean downFile(String filename);
}
