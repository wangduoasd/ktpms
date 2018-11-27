package com.kaituo.pms.dao;

import com.kaituo.pms.bean.FileUploadRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/13 16:23
 */
@Repository
@Mapper
public interface FileUploadRecordMapper {
    /**
     * 查询所有文件导入记录+++模糊查询
     * @param status
     * @param fileName
     * @return
     */
    List<FileUploadRecord> selectAllRecord(@Param("status")Integer status,@Param("fileName")String fileName);
    /**
     * 添加上传文件的记录
     * @param fileUploadRecord
     */
    void insertFileRecord(@Param("fileUploadRecord")FileUploadRecord fileUploadRecord);

    /**
     * 删除文件在数据库记录
     * @param fileName
     */
    void deleteFileRecord(@Param("fileName")String  fileName);

    /**
     *
     * 阅读量加一操作
     * @param id
     */
    void updateReading(Integer id);
    /**
     *
     * 下载量加一操作
     * @param id
     */
    void updateDownload(Integer id);

    /**
     * 根据文件名称，查询id
     * @param fileName
     */
    FileUploadRecord selectByFileName(String fileName);

    /**
     * 根据文件名，修改上传人姓名
     * @param userName
     * @param fileName
     */
    void updateUserNameByFileName(@Param("userName") String  userName,@Param("fileName")String fileName);
}
