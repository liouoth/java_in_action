package com.leo.thread.同步异步;

import java.io.FileNotFoundException;
import java.io.FileReader;

//asynchronous 异步
public class Async {
    public static void main(String[] args) {
        try {
//            new Thread(()->{new FileReader("")}).start(); io操作
            System.out.println("异步");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
