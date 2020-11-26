package com.leo.jmm.visibility.singleton;

public class InnerClassSingleton {
    private static class InnerClass{
        private static InnerClass a = new InnerClass();
    }

    private static InnerClass getInstance(){
        return InnerClass.a;
    }

    public static void main(String[] args) {
        InnerClassSingleton.getInstance();
    }
}
