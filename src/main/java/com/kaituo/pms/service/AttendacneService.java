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
}
