package com.leo.concurrent_tool.countdownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class GameTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        for (int i =0;i<10;i++){
            int finalI = i;
            pool.submit(
                    ()->{
                            Random random = new Random();
                            for (int j=0;j<100;){
                                sleep(400);
                                j+=random.nextInt(10);
                                log.debug("玩家：{}，进度：{}", finalI,j);
                            }
                            latch.countDown();
                    }
            );
        }
        try {
            log.debug("等待玩家就绪！！");
            latch.await();
            log.debug("游戏开始！！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
