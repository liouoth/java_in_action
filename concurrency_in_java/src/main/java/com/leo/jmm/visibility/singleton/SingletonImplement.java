package com.leo.jmm.visibility.singleton;

import java.io.Serializable;

public class SingletonImplement {
}

//饿汉式
final class Singleton implements Serializable{
    private Singleton(){}

    private static final Singleton INSTANCE = new Singleton();

    public static Singleton getInstance(){
        return INSTANCE;
    }

    public Object readResolve(){
        return INSTANCE;
    }
}

