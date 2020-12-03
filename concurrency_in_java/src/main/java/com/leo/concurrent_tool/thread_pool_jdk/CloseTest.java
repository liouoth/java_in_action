package com.leo.concurrent_tool.thread_pool_jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CloseTest {
    public static void showdownTest() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(
                ()->{
                    try {
                        Thread.sleep(1000);
                        log.debug("1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        pool.submit(
                ()->{
                    try {
                        Thread.sleep(1000);
                        log.debug("2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        pool.submit(
                ()->{
                    try {
                        Thread.sleep(1000);
                        log.debug("3");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread.sleep(500);
        pool.shutdown();
    }

    public static void showdownNowTest() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(
                ()->{
                    try {
                        Thread.sleep(1000);
                        log.debug("1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        pool.submit(
                ()->{
                    try {
                        Thread.sleep(1000);
                        log.debug("2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        pool.submit(
                ()->{
                    try {
                        Thread.sleep(1000);
                        log.debug("3");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread.sleep(500);
        log.debug(""+pool.shutdownNow().size());
    }

    public static void main(String[] args) throws InterruptedException {
        showdownNowTest();
    }
}
