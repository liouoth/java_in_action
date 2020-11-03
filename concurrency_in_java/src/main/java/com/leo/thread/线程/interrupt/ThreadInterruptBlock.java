package com.leo.thread.线程.interrupt;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadInterruptBlock {
    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                log.debug("会不会执行？");
            } catch (InterruptedException e) {
                log.debug("被打断了");
                e.printStackTrace();
            }
        });
        thread.start();
        log.debug("执行前：thread interrupt state:{}",thread.isInterrupted());
        //主线程start子线程之后，只是让子线程处于可运行状态，也就是说主线程在打断时，可能是打断子线程RUNNABLE状态
        //主线程sleep 1s后，子线程已经大概率除了sleep状态
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
        log.debug("执行后:thread interrupt state:{}",thread.isInterrupted());
    }
}
