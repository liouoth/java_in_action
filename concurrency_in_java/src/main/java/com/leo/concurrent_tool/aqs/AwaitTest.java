package com.leo.concurrent_tool.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class AwaitTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.newCondition().await();

    }
}
