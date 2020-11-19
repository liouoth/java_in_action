package com.leo.share_mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OOSyncCounter {
    class A{

    }
    //使用synchronized
    public static void main(String[] args) throws InterruptedException {
        //使用静态类
        A a = new OOSyncCounter().new A();
        Room room = new Room();
        Thread t_plus = new Thread(() -> {for (int i = 0; i < 5000; i++) {room.increment();} });
        Thread t_minus = new Thread(() -> {for (int i = 0; i < 5000; i++) {room.decrement(); } });
        t_plus.start();
        t_minus.start();
        t_plus.join();
        t_minus.join();
        log.debug("counter:{}",room.getCounter());
    }
}
//class
class Room{
    private int counter = 0;
    public void increment(){
        synchronized (this){
            counter++;
        }
    }
    public void decrement(){
        synchronized (this){
            counter--;
        }
    }

    public int getCounter() {
        return counter;
    }
}
