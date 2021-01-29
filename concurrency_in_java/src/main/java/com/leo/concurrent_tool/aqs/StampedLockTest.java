package com.leo.concurrent_tool.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

@Slf4j
public class StampedLockTest {
    public static void main(String[] args) {
        DataStamped stamped = new DataStamped(100);
        new Thread(()->{
            int read = stamped.read(2000);
            log.debug("read data : {}",read);
        },"t1").start();
        new Thread(()->{
            stamped.write(70);
        },"t2").start();
    }
}
@Slf4j
class DataStamped{
    private int data;
    private final StampedLock lock = new StampedLock();

    DataStamped(int data){
        this.data = data;
    }

    public int read(int readTime){
        long stamp = lock.tryOptimisticRead();
        log.debug("optimistic read locking");
            sleep(readTime);
        if (lock.validate(stamp)){
            log.debug("validate success:data not be changed");
            return this.data;
        }
        //锁升级，数据被改过
        log.debug("lock updating");
        try {
            stamp = lock.readLock();
            sleep(readTime);
            log.debug("read data:{}",data);
            return this.data;
        }finally {
            lock.unlockRead(stamp);
        }
    }

    //读没什么改变
    public void write(int data){
        log.debug("lock acquired");
        long stamp = lock.writeLock();
        log.debug("lock acquired,stamp:{}",stamp);
        try {
            this.data = data;
            log.debug("write data:{}",data);
        }finally {
            log.debug("write unlock");
            lock.unlockWrite(stamp);
        }
    }


    private void sleep(int readTime) {
        try {
            Thread.sleep(readTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
