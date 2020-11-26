package com.leo.nolock.atomic.adder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AdderDemo {
    public static <T> void demo(  //使用泛型，那么类需要添加泛型
            Supplier<T> supplier, //提供者
            Consumer<T> consumer    //消费者
    ){
        long start = System.nanoTime();
       T addr =  supplier.get();
        List<Thread> threads = new ArrayList<>();
       for (int i =0;i<40;i++){
           threads.add(new Thread(()->{
               for (int j=0;j<500000;j++){
                   consumer.accept(addr);
               }
           }));
       }
       threads.forEach(thread -> thread.start());
       threads.forEach(thread -> {
           try {
               thread.join();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       });
        long end = System.nanoTime();
        System.out.println("结果："+addr+", cost:"+(end-start)/1000_1000);
    }

    public static void main(String[] args) {
        for (int i=0;i<5;i++) {
            demo(
                    () -> new AtomicLong(),
                    (addr) -> addr.getAndIncrement()
            );
        }
        for (int i=0;i<5;i++) {
            demo(
                    ()->new LongAdder(),
                    (addr)->addr.increment()
            );
        }
    }
}
