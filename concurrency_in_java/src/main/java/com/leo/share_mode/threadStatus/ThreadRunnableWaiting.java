package com.leo.share_mode.threadStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadRunnableWaiting {
    final static Object o = new Object();
    public static void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                synchronized (o){
                    o.wait();
                    log.debug("线程运行完了！！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            try {
                synchronized (o){
                    o.wait();
                    log.debug("线程运行完了！！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t1.start();
        t2.start();
        //让t1 t2都进入等待，然后再notifyall，肯定有一个先获取到锁，一个后获取到
        Thread.sleep(2000);
        synchronized (o){
            o.notifyAll();
        }
    }

    public static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        t1.join();
        log.debug("等待完成！！");
    }
    public static void main(String[] args) throws InterruptedException {
        test2();
    }
}
