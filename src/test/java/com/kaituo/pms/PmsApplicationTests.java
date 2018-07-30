package com.kaituo.pms;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Timer;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
public class PmsApplicationTests {

    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.schedule(new a(), 10000);

    }
    @Scheduled(fixedDelay=2000)
    public static  void aa(){
        System.out.println("aa");
    }
}
