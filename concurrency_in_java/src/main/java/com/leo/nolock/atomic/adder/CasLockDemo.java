package com.leo.nolock.atomic.adder;

import java.util.concurrent.atomic.AtomicInteger;

public class CasLockDemo {
    static int i = 0;
    public static void main(String[] args) throws InterruptedException {
        SelfCasLock lock = new SelfCasLock();

        new Thread(
                ()->{
                    for (int j =0;j<1000;j++){
                        try{
                            lock.lock();
                            i++;
                            System.out.println("加操作");
                        }finally {
                            lock.unlock();
                        }
                    }
                }
        ).start();


        new Thread(
                ()->{
                    for (int j =0;j<1000;j++){
                        try{
                            lock.lock();
                            i--;
                            System.out.println("减操作");
                        }finally {
                            lock.unlock();
                        }
                    }
                }
        ).start();

        Thread.sleep(4000);
        System.out.println("循环结束："+i);
    }
}
class SelfCasLock{
    private AtomicInteger status = new AtomicInteger(0);

    public void lock(){
        while (true){
            if (status.compareAndSet(0,1)){
                break;
            }
        }
    }

    public void unlock(){
        status.set(0);
    }
}
