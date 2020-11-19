package com.leo.share_mode;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

public class OuterClass {
    //成员类
   class InnerClass{

    }

    public static void main(String[] args) {
       OuterClass outer = new OuterClass();
       InnerClass innerClass = outer.new InnerClass();
    }
}
