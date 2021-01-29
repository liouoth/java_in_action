package com.leo.concurrent_tool.countdownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        CountDownLatch downLatch = new CountDownLatch(3);
        service.submit(
                ()->{
                    log.debug("begin...");
                    sleep(1);
                    downLatch.countDown();
                    log.debug("end,get :{} ",downLatch.getCount());
                }
        );
        service.submit(
                ()->{
                    log.debug("begin...");
                    sleep(1);
                    downLatch.countDown();
                    log.debug("end,get :{} ",downLatch.getCount());
                }
        );
        service.submit(
                ()->{
                    log.debug("begin...");
                    sleep(1);
                    downLatch.countDown();
                    log.debug("end,get :{} ",downLatch.getCount());
                }
        );
        service.submit(
                ()->{
                    log.debug("waiting.....");
                    try {
                        downLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("wait end...");
                }
        );
    }
    private static void sleep(int i) {
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
