package com.leo.thread.线程.state;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
@Slf4j
public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
    private static void test() {
        Thread thread = new Thread(() -> {
            try {
                InputStream inputStream = new FileInputStream(new File("E:\\data\\a.txt"));
                while (inputStream.read(new byte[1024]) != -1) {
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        log.debug("主线程结束");
    }

    static Thread t2,t3;
    private static void test1() throws InterruptedException {
        t2 = new Thread(() -> {
            synchronized (ThreadState.class){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t3 = new Thread(() -> {
            synchronized (ThreadState.class){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();t3.start();
        Thread.sleep(1000);
        log.debug("t3 状态:{}",t3.getState());
    }
}
