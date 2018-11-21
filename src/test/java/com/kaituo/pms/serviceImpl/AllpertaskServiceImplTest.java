package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.service.AllpertaskService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/14 15:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AllpertaskServiceImplTest {

    @Autowired
    AllpertaskService allpertaskService;

    @Test
    public void distribute_Allpertask() throws SchedulerException {

        Allpertask allpertask = new Allpertask();
        allpertask.setAllpertask_name("第一个任务");
        allpertask.setAllpertask_difficulty(1);
        allpertask.setAllpertask_price(11);
        allpertask.setAllpertask_award(11);
        allpertask.setAllpertask_time(1);

        allpertask.setAllpertask_starttime(new Date(118,10,14,16,28,00));
        allpertask.setAllpertask_endtime(new Date(119,10,14,16,59,00));
        allpertask.setAllpertask_image("123");

        allpertaskService.distribute_Allpertask(allpertask);
    }

    @Test
    public void find_Allpertask_ofadmin() {
        System.out.println("-------------" +
                ""+allpertaskService.find_Allpertask_ofadmin().toString());
    }

    @Test
    public void find_allpertaskfinish() {
        System.out.println ("-------------------------------------------");
        System.out.println (allpertaskService.find_allpertaskfinish ());
    }

    @Test
    public void pass_allpertask() {
        allpertaskService.pass_allpertask (9,1);
    }

    @Test
    public void fail_allpertask() throws InterruptedException {
        allpertaskService.fail_allpertask (8,3);
    }

    @Test
    public void find_Allpertask_ofuser() {
        allpertaskService.find_Allpertask_ofuser (2,1);
    }

    @Test
    public void allpertaskList() {
        //allpertaskService.AllpertaskList ();
    }

    @Test
    public void get_Allpertask() throws Exception {
        allpertaskService.get_Allpertask(8,2);
        System.out.println (allpertaskService.get_Allpertask(8,3));
        allpertaskService.get_Allpertask(9,2);
        System.out.println (allpertaskService.get_Allpertask(9,1));
    }

    @Test
    public void giveup_allpertask() {
        allpertaskService.giveup_allpertask (8,2);
    }

    @Test
    public void finish_allpertask() {
        allpertaskService.finish_allpertask (9,1);
    }
}