package com.leo.share_mode.designPattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程t1,t2，顺序启动，实现输出 t1_log t2_log
 */
@Slf4j
public class FixOrderOutput {
    static boolean isOrder = false;

    public static void main(String[] args) {
//        useWaitNotify();
//        useParkUnpark();
        useReentrantLock();
//        test();
        synchronized (FixOrderOutput.class){

        }
    }

    //wait当然可以被打断，但是entry list不可以被打断啊，如果这个线程获取不到锁，那就会一直等待下去
    private static void test() throws InterruptedException {
        Object object = new Object();
        Thread thread = new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    log.debug("可以被打断啊！");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.interrupt();
        Thread.sleep(1000);
    }

    private static void useReentrantLock() {
        ReentrantLock lock = new ReentrantLock();
        Condition orderRoom = lock.newCondition();
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                orderRoom.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            log.debug("t1 log");
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                log.debug("t2 log");
                orderRoom.signal();
            } finally {
                lock.unlock();
            }
        }, "t2");
        t1.start();
        t2.start();
    }

    private static void useParkUnpark() {
        Thread t1 = new Thread(() -> {
            while (!isOrder) {
                LockSupport.park();
            }
            log.debug("t1 log");
        }, "t1");
        Thread t2 = new Thread(() -> {
            log.debug("t2 log");
            isOrder = true;
            LockSupport.unpark(t1);
        }, "t2");
        t1.start();
        t2.start();
    }


    private static void useWaitNotify() {
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object) {
                while (!isOrder) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("t1 log");
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (object) {
                log.debug("t2 log");
                isOrder = true;
                object.notifyAll();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
