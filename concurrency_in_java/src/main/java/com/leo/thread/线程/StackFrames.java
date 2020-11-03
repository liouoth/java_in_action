package com.leo.thread.线程;

//frame 帧
public class StackFrames {
    public static void main(String[] args) {
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
