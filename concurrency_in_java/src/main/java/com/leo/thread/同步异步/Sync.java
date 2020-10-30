package com.leo.thread.同步异步;

import java.io.FileNotFoundException;
import java.io.FileReader;

//synchronous 同步
public class Sync {
    public static void main(String[] args) {
        try {
            new FileReader("");
            System.out.println("同步");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
