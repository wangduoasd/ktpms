//package com.kaituo.pms;
//
//import com.kaituo.pms.bean.FileUploadRecord;
//import com.kaituo.pms.dao.FileUploadRecordMapper;
//import com.kaituo.pms.quartz.QuartzManager;
//import com.kaituo.pms.quartz.ScheduleTask;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PmsApplicationTests {
//    @Autowired
//    FileUploadRecordMapper fileUploadRecordMapper;
//    @Test
//    public void contextLoads() {
//        FileUploadRecord fileUploadRecord=new FileUploadRecord("123","234",1);
//        fileUploadRecordMapper.insertFileRecord(fileUploadRecord);
//        //fileUploadRecordMapper.deleteFileRecord(2);
//        for (FileUploadRecord f:fileUploadRecordMapper.selectAllRecord()
//             ) {
//            System.out.println(f.toString());
//        }
//        System.out.println("+++++++"+fileUploadRecordMapper.selectAllRecord().toString());
//
//    }
//}