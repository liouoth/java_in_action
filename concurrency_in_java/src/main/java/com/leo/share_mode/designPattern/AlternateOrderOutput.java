package com.leo.share_mode.designPattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程t1,t2,t3
 * t1 输出 a ,t2 输出 b , t3 输出 c
 * 交替输出5次 abcabcabcabcabc
 */
public class AlternateOrderOutput {
    public static void main(String[] args) {
//        useWaitNotify(); //容易犯错两个点，两个循环条件
//        useParkUnpark();
        useReentrantLock();
    }
    static Thread t1=null,t2=null,t3=null;
    private static void useParkUnpark() {
        t1 = new Thread(
                () -> {
                    while (order <= 16) {
                        while (order % 3 != 1) {
                                LockSupport.park();
                        }
                        System.out.print("a");
                        order++;
                        LockSupport.unpark(t2);
                    }
                }
                , "t1");
        t2 = new Thread(
                () -> {
                        while (order <= 16) {
                            while (order % 3 != 2) {
                                LockSupport.park();
                            }
                            System.out.print("b");
                            order++;
                            LockSupport.unpark(t3);
                    }
                }, "t2");
        t3 =new Thread(
                () -> {
                        while (order <= 16) {
                            while (order % 3 != 0) {
                                LockSupport.park();
                            }
                            System.out.print("c");
                            order++;
                            LockSupport.unpark(t1);
                    }
                }
                , "t3");
        t1.start();
        t2.start();
        t3.start();
    }

    static int order = 1;
    private static void useWaitNotify() {
        Object object = new Object();
        new Thread(
                () -> {
                    synchronized (object) {
                        while (order <= 16) {
                            while (order % 3 != 1) {
                                try {
                                    object.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.print("a");
                            order++;
                            object.notifyAll();
                        }
                    }
                }
                , "t1").start();
        new Thread(
                () -> {
                    synchronized (object) {
                        while (order <= 16) {
                            while (order % 3 != 2) {
                                try {
                                    object.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.print("b");
                            order++;
                            object.notifyAll();
                        }
                    }
                }
                , "t2").start();
        new Thread(
                () -> {
                    synchronized (object) {
                        while (order <= 16) {
                            while (order % 3 != 0) {
                                try {
                                    object.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.print("c");
                            order++;
                            object.notifyAll();
                        }
                    }
                }
                , "t3").start();
    }

    private static void useReentrantLock() {
        ReentrantLock lock = new ReentrantLock();
        Condition aRoom = lock.newCondition();
        Condition bRoom = lock.newCondition();
        Condition cRoom = lock.newCondition();
        new Thread(
                () -> {
                    try{
                        lock.lock();
                        while (order <= 16) {
                            while (order % 3 != 1) {
                                try {
                                    aRoom.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.print("a");
                            order++;
                            bRoom.signal();
                        }
                    }finally {
                        lock.unlock();
                    }
                }
                , "t1").start();
        new Thread(
                () -> {
                    try{
                        lock.lock();
                        while (order <= 16) {
                            while (order % 3 != 2) {
                                try {
                                    bRoom.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.print("b");
                            order++;
                            cRoom.signal();
                        }
                    }finally {
                        lock.unlock();
                    }
                }
                , "t2").start();
        new Thread(
                () -> {
                    try{
                        lock.lock();
                        while (order <= 16) {
                            while (order % 3 != 0) {
                                try {
                                    cRoom.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.print("c");
                            order++;
                            aRoom.signal();
                        }
                    }finally {
                        lock.unlock();
                    }
                }
                , "t3").start();
    }
}
