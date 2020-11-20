package com.leo.share_mode.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class BasicDemo {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        try{
            log.debug("enter main");
            lock.lock();
            m1();
        }finally {
            lock.unlock();
        }
    }

    private static void m1() {
        try{
            log.debug("enter m1");
            lock.lock(); //可重入
            m2();
        }finally {
            lock.unlock();
        }
    }

    private static void m2() {
        try{
            log.debug("enter m2");
            lock.lock(); //可重入
        }finally {
            lock.unlock();
        }
    }
}
