package com.leo.concurrent_tool.aqs;

import lombok.extern.slf4j.Slf4j;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.util.concurrent.locks.ReentrantReadWriteLock;


@Slf4j
public class WriteReadLockTest {
//    public static void main(String[] args) {
//        DataContainer dataContainer = new DataContainer();
//        new Thread(()->{
//            dataContainer.read();
//        },"t1").start();
//        new Thread(()->{
//            dataContainer.read();
//        },"t2").start();
//    }
    public static void main(String[] args) {
        DataContainer dataContainer = new DataContainer();
        new Thread(()->{
            dataContainer.write();
        },"t1").start();
        new Thread(()->{
            dataContainer.read();
        },"t2").start();
    }
}
@Slf4j
class DataContainer{
    private Object data;
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock w = readWriteLock.writeLock();
    public Object read(){
        log.debug("获取读锁");
        r.lock();
        try{
            log.debug("读取");
            Thread.sleep(1000);
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            r.unlock();
        }
    }

    public void write(){
        log.debug("获取写锁");
        w.lock();
        try{
            log.debug("写入");
            Thread.sleep(1000);
            log.debug("写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }
    }
}

