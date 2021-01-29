package com.leo.concurrent_tool.aqs;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInfo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.lock();
//        reentrantLock.unlock();
//        reentrantLock.lockInterruptibly();
        reentrantLock.newCondition().signal();
//        System.out.println(new Date());
    }
}
