package com.leo.thread.线程.join;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadJoinTime {
    static int i =0;
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {
        Thread t1 = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(2);
                i = 30;
            }
        };
        t1.start();
        log.debug("开始");
//        t1.join(1000);
        t1.join(3000);
        t1.join(3000);
        log.debug("i={}",i);
    }

}
