package jdk8.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamTest5 {
    public static void main(String[] args) throws InterruptedException {
//        IntStream.range(1,10).peek(StreamTest5::debug).sum();
        //一次4行，取决于核心数 cpu的核心数,可以修改
        //ForkJoinPool.commonPool-worker-2: debug:15
//        IntStream.range(1,100).parallel().peek(StreamTest5::debug).sum();
        //多次调用parallel 和串行函数，以最后一次为准

        //所有的并行流都是用 同一个线程池
        //自定义自己的线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        forkJoinPool.submit(()->{
            IntStream.range(1,100).parallel().peek(StreamTest5::debug).sum();
        });
        synchronized (forkJoinPool){
            forkJoinPool.wait();
        }
    }

    private static void debug(int i){
        System.out.println(Thread.currentThread().getName()+": debug:"+i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
