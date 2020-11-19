package com.leo.share_mode;

public class StringNotChange {
    public static void main(String[] args) {
        //final char value[]; 使用一个char数组存储String char，同时是不可变的
        String a = new String("aa");
        //当String出现  sub等操作时
        System.out.println(a.substring(0, 1));
    }
}
