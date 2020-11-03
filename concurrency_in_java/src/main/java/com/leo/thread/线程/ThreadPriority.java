package com.leo.thread.线程;

public class ThreadPriority {
    public static void main(String[] args) {
        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; ; i++) {
                        System.out.println("...................t2:" + i);
                    }
                }
        );
        Thread t1 = new Thread(
                () -> {
//                    Thread.yield();
                    for (int i = 0; ; i++) {
                        System.out.println("t1:" + i);
                    }
                }
        );
        t1.setPriority(10);
        t1.start();
        t2.start();
    }
}
