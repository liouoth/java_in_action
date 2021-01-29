package com.leo.concurrent_tool.countdownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(3);
        new Thread(()->{
            log.debug("begin...");
            sleep(1);
            downLatch.countDown();
            log.debug("end,get :{} ",downLatch.getCount());
        },"t1").start();
        new Thread(()->{
            log.debug("begin...");
            sleep(1);
            downLatch.countDown();
            log.debug("end,get : {}",downLatch.getCount());
        },"t2").start();
        new Thread(()->{
            log.debug("begin...");
            sleep(1);
            downLatch.countDown();
            log.debug("end,get : {}",downLatch.getCount());
        },"t3").start();
        log.debug("waiting.....");
        downLatch.await();
        log.debug("wait end...");
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
