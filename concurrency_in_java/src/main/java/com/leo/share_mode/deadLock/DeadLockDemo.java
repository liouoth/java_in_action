package com.leo.share_mode.deadLock;

public class DeadLockDemo {
    private static Object a = new Object();
    private static Object b = new Object();

    private static void t1() throws InterruptedException {
        synchronized (a){
            Thread.sleep(2000);
            synchronized (b){

            }
        }
    }
    private static void t2() throws InterruptedException {
        synchronized (b){
            Thread.sleep(2000);
            synchronized (a){

            }
        }
    }

    public static void main(String[] args) {
        new Thread(()->{
            try {
                t1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                t2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
