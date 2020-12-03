package com.leo.concurrent_tool.thread_pool_jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class VariesThreadPool {
    public static void newFixedThreadPoolTest(){
        ExecutorService threadPool = Executors.newFixedThreadPool(10,
                new ThreadFactory() {
                    private AtomicInteger i = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "mwq_"+i.getAndIncrement());
                    }
                });
        threadPool.execute(
                ()->{
                    log.debug("1");
                }
        );
        threadPool.execute(
                ()->{
                    log.debug("2");
                }
        );
        threadPool.execute(
                ()->{
                    log.debug("3");
                }
        );
    }


    //synchronizedQueue的使用
    public static void synchronizedQueueTest() throws InterruptedException {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        Thread t1 = new Thread(() -> {
            try {
                log.debug("putting:{}", 1);
                synchronousQueue.put(1);
                log.debug("{}: putted", 1);

                log.debug("putting:{}", 2);
                synchronousQueue.put(2);
                log.debug("{}: putted", 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();

        Thread.sleep(2000);

        Thread t2 = new Thread(() -> {
            log.debug("taking:{}", 1);
            try {
                synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t2.start();

        Thread.sleep(2000);

        Thread t3 = new Thread(() -> {
            log.debug("taking:{}", 2);
            try {
                synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");

        t3.start();
    }
    public static void newSingleThreadExecutorTest(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            int i = 1/0;
            log.debug("{}",i);
        });

        executorService.execute(()->{
            log.debug("{}",2);
        });

        executorService.execute(()->{
            log.debug("{}",3);
        });
    }

    public static void main(String[] args) throws InterruptedException {
        newSingleThreadExecutorTest();
    }
}
