package com.kaituo.pms.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/13 16:18
 */
@Data
public class FileUploadRecord {
    private Integer id;
    private String fileName;
    private String uploadfileUser;
    private Date createTime;

    public FileUploadRecord(String fileName, String uploadfileUser) {
        this.fileName = fileName;
        this.uploadfileUser = uploadfileUser;
    }
}
