package com.leo.nolock.unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

//实现自己封装的原子整数
public class MyAtomicInteger {
    //首先int值
    private volatile int data;

    //Unsafe对象,参考Atomic中的写法 会将Unsafe对象静态化
//    private static final Unsafe unsafe = Unsafe.getUnsafe();//初始化unsafe对象
    private static final Unsafe unsafe = GetUnSafe.getUnsafe();//初始化unsafe对象
    //data字段的静态偏移量
    private static final long valueOffset;

    public MyAtomicInteger(int data) {
        this.data = data;
    }

    static {
        //获取data的valueOffset 我感觉这个东西应该和对象的字节码有关，每个字段在类中偏移量是固定的
        try {
            //unsafe.objectFieldOffset获取对象的偏移量,获取静态字段偏移量staticFieldOffset
            valueOffset = unsafe.objectFieldOffset(
                    MyAtomicInteger.class.getDeclaredField("data")
            );
        }  catch (Exception ex) { throw new Error(ex); }
    }

    //自增操作
    public int increment(){
        int prev,next;
        do{
            prev = this.data;
            next = prev+1;
        }while (!unsafe.compareAndSwapInt(this,valueOffset,prev,next));
        return this.data;
    }
    //自减操作
    public int decrement(){
        int prev,next;
        do{
            prev = this.data;
            next = prev-1;
        }while (!unsafe.compareAndSwapInt(this,valueOffset,prev,next));
        return this.data;
    }

    public int get(){
        return this.data;
    }

    public static void main(String[] args) throws InterruptedException {
        MyAtomicInteger data = new MyAtomicInteger(0);
        Thread t1 = new Thread(
                ()->{
                    for (int i = 0;i<100000;i++) {
                        System.out.println(data.get());
                        data.increment();
                    }
                }
        );
        t1.start();

        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; i < 100000; i++) {
                        System.out.println(data.get());
                        data.decrement();
                    }
                }
        );
        t2.start();
        t1.join();
        t2.join();
        System.out.println(data.get());
    }

}
