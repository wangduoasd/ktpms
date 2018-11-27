package com.kaituo.pms.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/13 16:18
 */
@Data

public class FileUploadRecord {
    private Integer id;
    private String fileName;
    private Integer readingQuantity;
    private Integer downloads;
    private String uploadfileUser;
    private Integer status;
    private Date createTime;
    private List<FileRecord> fileRecords;

    public FileUploadRecord(String fileName, String uploadfileUser, Integer status) {
        this.fileName = fileName;
        this.uploadfileUser = uploadfileUser;
        this.status = status;
    }
    public FileUploadRecord(){}
}
