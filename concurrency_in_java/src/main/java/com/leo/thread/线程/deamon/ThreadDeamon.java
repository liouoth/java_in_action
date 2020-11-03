package com.leo.thread.线程.deamon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadDeamon {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Thread local = Thread.currentThread();
            while (true) {
                if (local.isInterrupted()) {
                    break;
                }
            }
        });
        t1.getState();
        t1.setDaemon(true);
        t1.start();
        log.debug("主线程结束");
    }
}
