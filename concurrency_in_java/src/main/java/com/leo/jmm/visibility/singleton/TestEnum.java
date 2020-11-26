package com.leo.jmm.visibility.singleton;

public enum TestEnum {
    A(1,"10");
    private int a;
    private String b;
    TestEnum(int a, String b){
        this.a = a;
        this.b = b;
    }

    TestEnum(){
        this.a = a;
        this.b = b;
    }
}
