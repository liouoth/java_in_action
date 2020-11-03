package com.leo.thread.线程.sleep;

import java.util.concurrent.TimeUnit;

public class ThreadSleepTimeUnit {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("2s later");
    }
}
