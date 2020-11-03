package com.leo.thread.线程.join;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadJoinSync {
    static int i =0;
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {
        Thread t1 = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(1);
                i = 30;
            }
        };
        Thread t2= new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(2);
                i = 20;
            }
        };
        t1.start();
        t2.start();
        log.debug("i={}",i);
        t1.join();
        log.debug("i={}",i);
        t2.join();
        log.debug("i={}",i);
    }


}
