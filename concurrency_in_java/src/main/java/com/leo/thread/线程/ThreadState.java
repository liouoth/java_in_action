package com.leo.thread.线程;

public class ThreadState {
    public static void main(String[] args) {
        Thread t = new Thread(
                ()->{
                    System.out.println(Thread.currentThread());
                }
        );
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
    }
}
