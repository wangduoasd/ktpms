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
     * 查询所有文件导入记录
     * @return
     */
    List<FileUploadRecord> selectAllRecord();
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
}
