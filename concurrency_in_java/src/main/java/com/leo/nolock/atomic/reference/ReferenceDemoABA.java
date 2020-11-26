package com.leo.nolock.atomic.reference;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ReferenceDemoABA {
    static AtomicReference<String> reference = new AtomicReference("A");
    public static void main(String[] args) throws InterruptedException {
        other();
        //是否能能改成功
        System.out.println(reference.compareAndSet("A", "B"));
    }

    public static void other() throws InterruptedException {
        Thread t1 = new Thread(
                () -> {
                    String prev = reference.get();
                    reference.compareAndSet(prev, "B");
                    System.out.println("A---->B");
                }
        );

        Thread t2 = new Thread(
                () -> {
                    String prev = reference.get();
                    reference.compareAndSet(prev, "A");
                    System.out.println("B---->A");
                }
        );
        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}
