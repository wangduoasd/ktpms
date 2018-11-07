package com.kaituo.pms;

import com.kaituo.pms.quartz.QuartzManager;
import com.kaituo.pms.quartz.ScheduleTask;
import com.kaituo.pms.quartz.Start;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsApplicationTests {
@Autowired
private QuartzManager quartzManager;
    @Test
    public void contextLoads() {

        try {
            quartzManager.addjob ("first",
                    "firstGroup",
                    "cronTrigger1",
                    "triggerGroup1",
                    ScheduleTask.class,
                    "0 42 21 6 11 ?",
                    "0");
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

}
