package com.leo.share_mode.monitor;

public class StaticFuncTest {
    public static synchronized void f(){

    }

    public static void main(String[] args) {
        f();
    }
}
