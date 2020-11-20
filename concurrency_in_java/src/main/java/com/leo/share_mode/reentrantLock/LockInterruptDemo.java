package com.leo.share_mode.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                //如果没竞争，则调用这个方法会或得到锁并进行加锁
                //如果有竞争，那么进入等待，等待过程中可以被打断，并捕获到打断异常
                lock.lockInterruptibly();
            } catch (Exception e) {
                e.printStackTrace();
                return;
//                lock.lock(); //lockInterruptibly 会进行加锁，不需要使用
            }
            try{
                //不会也是根据monitor实现的吧
                log.debug("获取到锁了！！"); //这里就是获得到锁了 可以解锁了，否则会出现 IllegalMonitorStateException
            }
            finally {
                lock.unlock();
            }
        });
        lock.lock();
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
