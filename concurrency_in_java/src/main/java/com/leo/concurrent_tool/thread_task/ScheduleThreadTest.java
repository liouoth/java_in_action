package com.leo.concurrent_tool.thread_task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleThreadTest {
    public static void task1Sleep() {
        //设置的实际上是核心线程数
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.schedule(
                () -> {
                    log.debug("=====task 1 =====");
                    sleep(2000);
                }, 1000, TimeUnit.MILLISECONDS
        );

        scheduledThreadPool.schedule(
                () -> {
                    log.debug("=====task 2 =====");
                }, 1000, TimeUnit.MILLISECONDS
        );
    }

    public static void task1Exception() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.schedule(
                () -> {
                    log.debug("=====task 1 =====");
                    throw new RuntimeException();
                }, 1000, TimeUnit.MILLISECONDS
        );

        scheduledThreadPool.schedule(
                () -> {
                    log.debug("=====task 2 =====");
                }, 1000, TimeUnit.MILLISECONDS
        );
    }

    public static void taskInternal1() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.scheduleAtFixedRate(
                ()->{
                  log.debug("哈哈哈");
                  sleep(2000);
                },1000,1000,TimeUnit.MILLISECONDS
        );
    }

    public static void taskInternal2() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.scheduleWithFixedDelay(
                ()->{
                    log.debug("哈哈哈");
                    sleep(2000);
                },1000,1000,TimeUnit.MILLISECONDS
        );
    }

    public static void main(String[] args) {
//        task1Sleep();
//        task1Exception();
        taskInternal2();
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
