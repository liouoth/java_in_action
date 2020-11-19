package com.leo.share_mode.monitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitNotiTest {
    static final Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("线程执行....");
            synchronized (obj) {
                try {
                    obj.wait(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("代码执行....");
        },"t1");
        Thread t2 = new Thread(() -> {
            log.debug("线程执行....");
            synchronized (obj) {
                try {
                    obj.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("代码执行....");
        },"t2");
        t1.start();
        t2.start();
        Thread.sleep(2000);
//        synchronized (obj){
//            obj.notify();
////            obj.notifyAll();
//        }
    }
}
