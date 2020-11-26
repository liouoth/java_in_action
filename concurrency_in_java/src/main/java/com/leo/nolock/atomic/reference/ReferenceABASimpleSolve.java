package com.leo.nolock.atomic.reference;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ReferenceABASimpleSolve {
    static AtomicMarkableReference<String> reference = new AtomicMarkableReference("A",true);
    public static void main(String[] args) throws InterruptedException {
        boolean isMarked = reference.isMarked();//是否更改过
        other();
        //是否能能改成功
        System.out.println(reference.compareAndSet("A", "B",isMarked,reference.isMarked()));
    }

    public static void other() throws InterruptedException {
        Thread t1 = new Thread(
                () -> {
                    String prev = reference.getReference();
                    reference.compareAndSet(prev, "B",reference.isMarked(),!reference.isMarked());
                    System.out.println("A---->B");
                }
        );

        Thread t2 = new Thread(
                () -> {
                    String prev = reference.getReference();
                    reference.compareAndSet(prev, "A",reference.isMarked(),!reference.isMarked());
                    System.out.println("B---->A");
                }
        );
        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}
