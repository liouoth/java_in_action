package com.leo.thread.线程;

public class ThreadVsRun {
    public static void main(String[] args) {
        Thread t = new Thread(
                ()->{
                    System.out.println(Thread.currentThread());
                }
        );
        t.start();
        t.setName("xiancheng");
        t.run();//主线程调用run
    }
}
