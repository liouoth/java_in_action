package com.leo.thread.线程.interrupt;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadInterruptRunning {
    public static void main(String[] args) throws InterruptedException {
//        test1();
        new Thread(()->{
            Thread.currentThread().interrupt();
        }).start();
    }

    private static void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
        });
        thread.interrupt();
        Thread.sleep(1000);
        log.debug("thread state: "+thread.isInterrupted());
    }

    private static void test() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Thread temp = Thread.currentThread();
            while (true){
                log.debug("运行");
                //先获取当前线程
                if (temp.isInterrupted()){
                    log.debug("被打断了，停止");
                    break;
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
