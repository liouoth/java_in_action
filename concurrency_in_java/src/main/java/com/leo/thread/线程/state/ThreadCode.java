package com.leo.thread.线程.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadCode {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.debug("洗水壶");
                Thread.sleep(1000);
                log.debug("烧水");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "小王");
        Thread t2 = new Thread(() -> {
            try {
                log.debug("洗杯子");
                Thread.sleep(1000);
                log.debug("洗茶壶");
                Thread.sleep(1000);
                log.debug("拿茶叶");
                Thread.sleep(1000);
                t1.join();
                log.debug("泡茶");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "老王");
        t1.start();
        t2.start();
    }
}
