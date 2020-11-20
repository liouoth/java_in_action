package com.leo.share_mode.designPattern.optimize;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        LockWrapper lockWrapper = new LockWrapper(5);
        Condition aCondition = lockWrapper.newCondition();
        Condition bCondition = lockWrapper.newCondition();
        Condition cCondition = lockWrapper.newCondition();
        new Thread(()->lockWrapper.print(aCondition,bCondition,"a")).start();
        new Thread(()->lockWrapper.print(bCondition,cCondition,"b")).start();
        new Thread(()->lockWrapper.print(cCondition,aCondition,"c")).start();
        Thread.sleep(2000);
        try{
            lockWrapper.lock();
            aCondition.signal();
        }finally {
            lockWrapper.unlock();
        }
    }
}
class LockWrapper extends ReentrantLock {
    private int circle; //循环的次数

    public LockWrapper(int circle) {
        this.circle = circle;
    }

    public void print(Condition condition,Condition nextCondition, String output) {
        for (int i = 0; i < circle; i++) {
            try {
                this.lock();
                condition.await();
                System.out.print(output);
                nextCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.unlock();
            }
        }
    }
}