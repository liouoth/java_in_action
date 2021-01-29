package com.leo.share_mode.parkunpark;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ParkUnparkTest {
    //先进行unpark再park
    public static void unparkFirst() throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.debug("线程开始啦!!!!");
            //当前线程暂停
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("线程park!!!!");
            LockSupport.park();
            log.debug("线程苏醒啦！！！");
        });
        thread.start();
        log.debug("主线程睡眠！！！");
        Thread.sleep(1000);
        log.debug("unpark 线程！");
        LockSupport.unpark(thread);
    }

    //用于线程的暂停以及恢复
    public static void parkFirst() throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.debug("线程开始啦!!!!");
            //当前线程暂停
            log.debug("线程暂停!!!!");
            LockSupport.park();
            log.debug("线程苏醒啦！！！");
        });
        thread.start();
        log.debug("主线程睡眠！！！");
        Thread.sleep(2000);
        log.debug("unpark thread 啦啦啦啦！");
        LockSupport.unpark(thread);
    }


    //测试park时能够被打断
    public static void parkTest1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("开始！！！");
            LockSupport.park(); //park当前线程
            log.debug("结束！！！");

        });
        t1.start();
        Thread.sleep(2000);
        log.debug("打断！！！");
        t1.interrupt();
        log.debug("是否被打断："+t1.isInterrupted());
    }

    //打断之后还能够park住？
    public static void parkTest2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("开始！！！");
            LockSupport.park(); //park当前线程
            log.debug("结束！！！");
        });
        t1.start();
        Thread.sleep(2000);
        log.debug("打断！！！");
        t1.interrupt();
        t1.start();
        t1.interrupt();
    }


    public static void main(String[] args) throws InterruptedException {
        parkTest2();
    }
}
