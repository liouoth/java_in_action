package com.leo.share_mode.monitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepWaitTest {
    static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (object){
                log.debug("获得了锁，但是我要等待去了！");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(100);
        synchronized (object){
            log.debug("获得了锁！");
        }
    }
}
