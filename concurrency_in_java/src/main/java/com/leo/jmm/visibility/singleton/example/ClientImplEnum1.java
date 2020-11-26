package com.leo.jmm.visibility.singleton.example;

public enum ClientImplEnum1 implements ClientEnumInterface{
    INSTANCE;

    @Override
    public String getConnection() {
        return "连接1";
    }

    @Override
    public String getName() {
        return "连接1";
    }

    public static void main(String[] args) {
        System.out.println(INSTANCE.getName());
    }
}
