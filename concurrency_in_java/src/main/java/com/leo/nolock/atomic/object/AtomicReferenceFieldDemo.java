package com.leo.nolock.atomic.object;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldDemo {
    private static Simple s = new Simple();
    private static Simple b = new Simple(); //b只是个对象引用并不会改变

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            for(int i=0;i<10000;i++){
                s.a++;
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<10000;i++){
                s.a--;
            }
        }).start();
        Thread.sleep(2000);
        System.out.println("1结束了："+s.a);

        AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(Simple.class,"a");
        new Thread(()->{
            for(int i=0;i<10000;i++){
                updater.getAndIncrement(b);
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<10000;i++){
                updater.getAndDecrement(b);
            }
        }).start();
        Thread.sleep(2000);
        System.out.println("2结束了："+b.a);
    }
}
class Simple{
    public volatile Integer a = 0; //这个必须是volatile的
}