package com.leo.object;

public class StaticVar {
    static String a = "123";

    public static void main(String[] args) {
        System.out.println(a);
        a = "sjdkajskdjkasjd";
        System.out.println(a);
    }
}
