package com.kaituo.pms.bean;

import lombok.Data;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/21 18:01
 */
@Data
public class FileRecord {
    private Integer id;
    private Integer fileId;
    private String readingUserName;
    private String downingUserName;

    public FileRecord(Integer fileId, String readingUserName, String downingUserName) {
        this.fileId = fileId;
        this.readingUserName = readingUserName;
        this.downingUserName = downingUserName;
    }
}
