package com.leo.concurrent_tool.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class SyncLockCustomerTest {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(()->{
            lock.lock();
            try {
                log.debug("locking...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        },"t1").start();
        new Thread(()->{
            lock.lock();
            try {
                log.debug("locking...");
            }finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        },"t2").start();
    }
}

class MyLock implements Lock {
    class MySync extends AbstractQueuedSynchronizer{
        //独占锁，需要实现 tryAcquire、tryRelease、isHeldExclusively
        @Override
        protected boolean tryAcquire(int arg) {
            //加锁
            if (arg == 1){
                if (compareAndSetState(0,1)){
                    setExclusiveOwnerThread(Thread.currentThread()); //    exclusiveOwnerThread Thread引用对象
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (arg==1){
                if (getState()==0){
                    throw new IllegalMonitorStateException();
                }
                //state是volatile，然后volatile写屏障会加在setState之后，那么将state的赋值操作放在其他操作之后
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
            return false;
        }

        //是否持有独占锁
        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        //返回条件变量
        protected Condition newCondition() {
            return new ConditionObject();
        }
    }

    MySync sync = new MySync();

    //尝试加锁，不成功就进入等待队列
    @Override
    public void lock() {
        sync.acquire(1);
    }

    //尝试加锁，不成功，进入等待队列，可打断
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    //尝试加锁，不成功返回，不进入队列
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    //尝试加锁，不成功返回，进入队列，有时限
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    //释放锁
    @Override
    public void unlock() {
        sync.release(1);
    }

    //生成条件变量
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
