package com.kaituo.pms.service;

import com.kaituo.pms.bean.Allpertask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collection;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.*;

/**
 * @author wangduo
 * @date 2018/11/7 - 22:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AllpertaskServiceTest {
    @Autowired
    AllpertaskService allpertaskService;
    @Test
    public void distribute_Allpertask() throws ParseException, SchedulerException {
        Allpertask allpertask=new Allpertask ();
        allpertask.setAllpertask_name ("扫地");
        allpertask.setAllpertask_difficulty (3);
        allpertask.setAllpertask_price (10);
        allpertask.setAllpertask_award (20);
        allpertask.setAllpertask_time (30);
        Calendar calendar = Calendar.getInstance ();
        calendar.set (2018,11,8,10,20,0);
        allpertask.setAllpertask_starttime (calendar.getTime ());
        allpertask.setAllpertask_endtime (new java.util.Date (118,10,8,22,0,0));
     allpertaskService.distribute_Allpertask (allpertask);
    }

    @Test
    public void delete_Allpertask() {
          Date bb=new Date(1970);
          Date aa=new Date(1900,1,1);

    }

    public  void aa(String str){
         str+="aaaaa";
    }
    @Test
    public  void update_Allpertask() {

         String str="ssss";
         str="111";
        aa(str);
        System.out.println (str);
    }
}