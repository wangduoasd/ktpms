package com.kaituo.pms.dao;

import com.kaituo.pms.bean.FileRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/21 19:00
 */
@Repository
@Mapper
public interface FileRecordMapper {
    /**
     * 查询所有对文件操作的记录
     */
    List<FileRecord> selectAllFileRecord();

    /**
     * 增加对文件操作的记录
     * @param fileRecord
     */
    void insertRecord(@Param("fileRecord") FileRecord fileRecord);

    /**
     * 文件被删除后，同时删除此文件的记录
     * @param id
     */
    void deleteByFileId(Integer id);

}
