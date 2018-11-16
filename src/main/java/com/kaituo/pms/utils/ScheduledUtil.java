//package com.kaituo.pms.utils;
//
//import com.kaituo.pms.dao.AttendanceMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
///**
// * spring 注解执行定时任务;
// * @author @chnxy_xrabbit
// * @date 2018/9/26 15:22
// */
//@Slf4j
//@Service
//public class ScheduledUtil {
//    @Autowired
//    AttendanceMapper attendanceMapper;
//    /**
//     * spring 注解执行定时任务;
//     */
//    @Scheduled(cron = "0 0 0 1 * ? ")
//    public void updateSolrStore(){
//        attendanceMapper.deleteAll();
//    }
//
//}
