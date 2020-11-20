package com.leo.share_mode.designPattern.optimize;

import java.util.concurrent.locks.LockSupport;

public class UsePark {
    private int loopNumber;
    private Thread [] threads; //0 1 2

    public UsePark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String output){
        for (int i = 0;i<loopNumber;i++){
            LockSupport.park();
            System.out.print(output);
            unpark(Thread.currentThread());
        }
    }

    private void unpark(Thread currentThread) {
        for (int i = 0;i<threads.length;i++){
            if (currentThread==threads[i]){
                int next = (i+1)%3;
                LockSupport.unpark(threads[next]);
            }
        }
    }

    public void setThreads(Thread[] threads) {
        this.threads = threads;
    }

    private void start() {
        for (Thread t:threads){
            t.start();
        }
        LockSupport.unpark(threads[0]); //重复start，Exception in thread "main" java.lang.IllegalThreadStateException
    }

    public static void main(String[] args) {
        UsePark usePark = new UsePark(5);
        usePark.setThreads(
                new Thread[]{
                        new Thread(() -> usePark.print("a")),
                        new Thread(() -> usePark.print("b")),
                        new Thread(() -> usePark.print("c"))
                }
        );
        usePark.start();
    }
}
