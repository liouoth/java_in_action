package com.leo.share_mode.GuardedSuspession;

public class JoinYuanli {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join(); //拿到的就是线程中的锁，Thread的锁，因为使用synchronized关键字
//        try {
//            new Thread().join();
//            new Object().wait(0);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
