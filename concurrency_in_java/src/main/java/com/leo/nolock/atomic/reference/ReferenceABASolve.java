package com.leo.nolock.atomic.reference;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ReferenceABASolve {
    static AtomicStampedReference<String> reference = new AtomicStampedReference("A",0);
    public static void main(String[] args) throws InterruptedException {
        int version = reference.getStamp();//先获取版本号
        other();
        //是否能能改成功
        System.out.println(reference.getStamp()+":"+reference.getReference());
        System.out.println(reference.compareAndSet("A", "B",version,version+1));
    }

    public static void other() throws InterruptedException {
        Thread t1 = new Thread(
                () -> {
                    String prev = reference.getReference();
                    reference.compareAndSet(prev, "B",reference.getStamp(),reference.getStamp()+1);
                    System.out.println("A---->B");
                }
        );

        Thread t2 = new Thread(
                () -> {
                    String prev = reference.getReference();
                    reference.compareAndSet(prev, "A",reference.getStamp(),reference.getStamp()+1);
                    System.out.println("B---->A");
                }
        );
        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}
