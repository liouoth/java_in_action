package com.leo.thread.线程.join;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadJoinFunc {
    static int i = 0;
    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    private static void test() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                log.debug("子进程开始");
                i = 10;
                log.debug("子进程结束");
            }
        };
        t1.start();
        log.debug("i={}", i);
    }

    private static void test2() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                log.debug("子进程开始");
                i = 10;
                log.debug("子进程结束");
            }
        };
        t1.start();
        t1.join();
        log.debug("i={}", i);
    }
}
