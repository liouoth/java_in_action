package com.leo.share_mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncCounter {
    static int counter = 0;
    //使用synchronized
    static Object Lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t_plus = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (Lock){
                    counter++;
                }
            }
        });
        Thread t_minus = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (Lock){
                    counter--;
                }
            }
        });
        t_plus.start();
        t_minus.start();
        t_plus.join();
        t_minus.join();
        log.debug("counter:{}",counter);
    }
}
