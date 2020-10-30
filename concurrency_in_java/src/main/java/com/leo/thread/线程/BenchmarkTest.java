package com.leo.thread.线程;


import org.openjdk.jmh.annotations.*;

import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//使用benchMark进行测试，创建一个多线程方法以及一个单线程方法
//分别在多核和单核的环境中测试
//@Fork
//@BenchmarkMode(Mode.AverageTime)
//@Warmup(iterations = 3)
//@Measurement(iterations = 2)
public class BenchmarkTest {
    static int [] ARRAY1 = new int[100000000]; //1亿个数
    static int [] ARRAY2 = new int[25000000]; //1亿个数
    static {
        Arrays.fill(ARRAY1,1);
        Arrays.fill(ARRAY2,1);
    }


//    @Benchmark  //4个线程分别计算25000000个数
    public int multipleThread() throws ExecutionException, InterruptedException {
        final int [] array = ARRAY2;
        FutureTask<Integer> t1 = new FutureTask<>(
                ()->{
                    Integer sum=0;
                    for (int i : array){
                        sum+=i;
                    }
                    return sum;
                }
        );
        FutureTask<Integer> t2 = new FutureTask<>(
                ()->{
                    Integer sum=0;
                    for (int i : array){
                        sum+=i;
                    }
                    return sum;
                }
        );
        FutureTask<Integer> t3 = new FutureTask<>(
                ()->{
                    Integer sum=0;
                    for (int i : array){
                        sum+=i;
                    }
                    return sum;
                }
        );
        FutureTask<Integer> t4 = new FutureTask<>(
                ()->{
                    Integer sum=0;
                    for (int i : array){
                        sum+=i;
                    }
                    return sum;
                }
        );
        new Thread(t1).start();;
        new Thread(t2).start();;
        new Thread(t3).start();;
        new Thread(t4).start();
        return t1.get()+t2.get()+t3.get()+t4.get();
    }

//    @Benchmark//一个线程计算100000000
    public int singleThread() throws ExecutionException, InterruptedException {
        final int [] array = ARRAY1;
        FutureTask<Integer> t1 = new FutureTask<>(
                ()->{
                    Integer sum=0;
                    for (int i : array){
                        sum+=i;
                    }
                    return sum;
                }
        );
        new Thread(t1).start();
        return t1.get();
    }


}
