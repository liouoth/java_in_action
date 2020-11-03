package com.leo.thread.线程;

import java.util.concurrent.TimeUnit;

public class SleepAvoidCpu100 {
    public static void main(String[] args) {
//        while (true){
//        }
        //加入sleep防止空转
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
