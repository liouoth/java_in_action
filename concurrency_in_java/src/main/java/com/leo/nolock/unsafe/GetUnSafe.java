package com.leo.nolock.unsafe;

import sun.misc.Lock;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class GetUnSafe {
    private static Unsafe unsafe;
    static{
        try{
            Field theUnSafe  = Unsafe.class.getDeclaredField("theUnsafe");
            theUnSafe.setAccessible(true);
            unsafe = (Unsafe) theUnSafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static Unsafe getUnsafe(){
        return unsafe;
    }

    public static void main(String[] args) {
//        Unsafe unsafe = new Unsafe(); //构造函数private，不能通过构造函数获得
//        Unsafe unsafe = Unsafe.getUnsafe(); //好像可以直接根据静态方法获得
//        AtomicInteger atomicInteger = new AtomicInteger();
    }
}
