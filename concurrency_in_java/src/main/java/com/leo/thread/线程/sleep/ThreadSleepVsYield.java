package com.leo.thread.线程.sleep;

public class ThreadSleepVsYield {
    public static void main(String[] args) throws InterruptedException {
//        Thread t = new Thread(
//                ()->{
//                    //这个是static方法
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//        );
//        t.start();
//        t.join(50);
//        System.out.println(t.getState());
        Thread thread = new Thread(
                ()->{
                    try {
                        System.out.println("sleep！");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        //InterruptedException 异常就是被打断的异常
                        System.out.println("wake up！");
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        Thread.sleep(500);
        System.out.println(thread.getState());
        System.out.println("interrupt！");
        thread.interrupt();
        System.out.println(thread.getState());
    }
}
