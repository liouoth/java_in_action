package com.leo.share_mode.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockTimeoutDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            //这样只会在执行到代码执行处时获取一下，如果获取不到就直接过去
//            if (!lock.tryLock()){
//                log.debug("没有获取到锁哦！！！");
//                return;
//            }
            try {
                if (!lock.tryLock(2, TimeUnit.SECONDS)){
                    log.debug("没有获取到锁哦！！！");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("获取到锁了！！！");
        });
        lock.lock();
        log.debug("开始子线程！！");
        t1.start();
        Thread.sleep(1000);
        lock.unlock();
    }
}
