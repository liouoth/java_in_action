package com.leo.nolock.atomic.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AtomicIntegerArrayDemo {
    /**
     参数1，提供数组、可以是线程不安全数组或线程安全数组
     参数2，获取数组长度的方法
     参数3，自增方法，回传 array, index
     参数4，打印数组的方法
     */
    private static <T> void demo(
            Supplier<T> arraySupplier,
            Function<T, Integer> lengthFun,
            BiConsumer<T, Integer> putConsumer,
            Consumer<T> printConsumer ) {
        List<Thread> ts = new ArrayList<>();
        T array = arraySupplier.get();
        int length = lengthFun.apply(array);
        for (int i = 0; i < length; i++) {
            // 每个线程对数组作 10000 次操作
            ts.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j%length);
                }
            }));
        }
        long start = System.nanoTime();
        ts.forEach(t -> t.start()); // 启动所有线程
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); // 等所有线程结束
        printConsumer.accept(array);
        long end = System.nanoTime();
        System.out.println("总耗时:"+(end-start));
    }

    public static void main(String[] args) {
        for (int i = 0;i<5;i++){
            demo(
                    ()->new int [10],
                    (array)->array.length,
                    // (array,index)->{synchronized (array){array[index]++;}},   //这个操作不是原子的,加上synchronized解决
                    (array,index)->array[index]++,
                    (array)-> System.out.println(Arrays.toString(array))
            );
        }
        for (int i = 0;i<5;i++){
            demo(
                    ()->new AtomicIntegerArray(10),
                    (array)->array.length(),
                    // (array,index)->{synchronized (array){array[index]++;}},   //这个操作不是原子的,加上synchronized解决
                    (array,index)->array.getAndIncrement(index),
                    (array)-> System.out.println(array.toString())
            );
        }
    }
}
