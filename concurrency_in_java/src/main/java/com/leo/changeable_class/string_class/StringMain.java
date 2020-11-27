package com.leo.changeable_class.string_class;

public class StringMain {
    public static void main(String[] args) {
        String a = "aaas";
        String s = a.replace("a", "a");
        System.out.println(a==s);

        String c = "vvvvv";
        String d = new String(c); //创建了一个新的对象，并将value 以及 hash值赋值
        System.out.println(c==d);
    }
}
