package com.leo.nolock.atomic.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);
        //cas，循环搭配
//        i.compareAndSet(10,20); // expect update
        //原子底层就实现了cas操作
        //原子自增,自减
//        i.incrementAndGet(); //++i
//        i.getAndIncrement();//i++

        //获取
//        i.getAndAdd(5); //获取并增加

        //update lambda表达式，并且原子
        i.updateAndGet(x->
            x*x
        );

        //实现一个updateAndGet
//        while (true){
//            int prev = i.get();
//            int next = prev*prev;
//            if (i.compareAndSet(prev,next)){
//                break;
//            }
//        }

        i.updateAndGet(
                x->x*x
        );
        System.out.println(i.get());
    }
}
