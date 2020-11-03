package com.leo.thread.线程;

//frame 帧
public class StackFramesThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
           m1(20);
        });
        t1.setName("t1");
        t1.start();
        m1(10);
    }

    private static void m1(int x){
        System.out.println("x");
        m2(x);
    }

    private static void m2(int x) {
        System.out.println("x2");
    }
}
