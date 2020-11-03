package com.leo.thread.线程.interrupt;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ThreadInterruptPark {
    static Thread thread;

    public static void main(String[] args) throws InterruptedException {
        start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    private static void start() {
        thread = new Thread(() -> {
            log.debug("进入thread");
            LockSupport.park(); //调用park，会在这直接阻塞
            log.debug("end the thread");
            //显示是被打断过
//            log.debug("thread isInterrupted state :{}",Thread.currentThread().isInterrupted());
            log.debug("thread isInterrupted state :{}",Thread.interrupted());//获取之后会重置
            //打断之后，在进行park,因为打断标记为false,于是park可以继续调用
            LockSupport.park(); //当isInterrupted为true时，再次使用park时无效，native方法
            log.debug("unpark");
        });
        thread.start();
    }

}
