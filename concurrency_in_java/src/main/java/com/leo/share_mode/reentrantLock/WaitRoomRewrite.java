package com.leo.share_mode.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class WaitRoomRewrite {
    static ReentrantLock lock = new ReentrantLock();
    static Condition cigaretteRoom = lock.newCondition();
    static Condition takeoutRoom = lock.newCondition();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                lock.lock();
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        cigaretteRoom.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟了，可以开始干活了");
            } finally {
                lock.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            try {
                lock.lock();
                log.debug("外卖到没？[{}]", hasCigarette);
                while (!hasTakeout) {
                    log.debug("没外卖，先歇会！");
                    try {
                        takeoutRoom.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有外卖了，可以开始干活了");
            } finally {
                lock.unlock();
            }
        }, "小女").start();

        Thread.sleep(1000);

        new Thread(() -> {
            log.debug("烟到了噢！");
            hasCigarette = true;
            try{
                lock.lock();
                cigaretteRoom.signal();
            }finally {
                lock.unlock();
            }
        }, "送烟的").start();
    }
}
