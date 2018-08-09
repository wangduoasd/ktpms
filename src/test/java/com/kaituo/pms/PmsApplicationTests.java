package com.kaituo.pms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);
        final long awaitTime = 20 * 1000;
        // 创建并执行在给定延迟后启用的一次性操作。
        exec.schedule(new Runnable() {
            public void run() {
                System.out.println("The 5thread can only run once!");
            }
        }, 5000, TimeUnit.MILLISECONDS);
        exec.schedule(new Runnable() {
            public void run() {
                System.out.println("The 4thread can only run once!");
            }
        }, 4000, TimeUnit.MILLISECONDS);

        exec.schedule(new Runnable() {
            public void run() {
                System.out.println("The 3thread can only run once!");
            }
        }, 3000, TimeUnit.MILLISECONDS);
        exec.schedule(new Runnable() {
            public void run() {
                System.out.println("The 15thread can only run once!");
            }
        }, 15000, TimeUnit.MILLISECONDS);
        exec.schedule(new Runnable() {
            public void run() {
                System.out.println("The 25thread can only run once!");
            }
        }, 25000, TimeUnit.MILLISECONDS);
        try {
            // 向学生传达“问题解答完毕后请举手示意！”
            exec.shutdown();

            // 向学生传达“XX分之内解答不完的问题全部带回去作为课后作业！”后老师等待学生答题
            // (所有的任务都结束的时候，返回TRUE)
            if (!exec.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                exec.shutdownNow();
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            System.out.println("awaitTermination interrupted: " + e);
            exec.shutdownNow();
        }
        exec.shutdown();
    }
}
