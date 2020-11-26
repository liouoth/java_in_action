package com.leo.jmm.visibility.singleton.example;

//比如说创建一个单例的线程池
public enum ClientTraditionalEnum {
    INSTANCE;

    ClientTraditionalEnum(){
        System.out.println("创建线程池！");
    }

    private String getConnect(){
        System.out.println("获取连接");
        return "连接";
    }

    public static void main(String[] args) {
        System.out.println(ClientTraditionalEnum.INSTANCE.getConnect());
    }
}
