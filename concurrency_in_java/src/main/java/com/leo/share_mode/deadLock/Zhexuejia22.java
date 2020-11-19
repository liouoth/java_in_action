package com.leo.share_mode.deadLock;

import lombok.extern.slf4j.Slf4j;

public class Zhexuejia22 {
    public static void main(String[] args) {
        Chopstick2 c1,c2,c3,c4,c5;
        c1= new Chopstick2();c2= new Chopstick2();c3= new Chopstick2();c4= new Chopstick2();c5= new Chopstick2();
        new Man2(c1,c2,"鲁迅").start();
        new Man2(c2,c3,"冰心").start();
        new Man2(c3,c4,"金庸").start();
        new Man2(c4,c5,"郭敬明").start();
        new Man2(c1,c5,"韩寒").start();
    }
}

@Slf4j
class Man2 extends Thread{
    private Chopstick2 Lock1;
    private Chopstick2 Lock2;

    Man2(Chopstick2 Lock1, Chopstick2 Lock2, String name)
    {
        this.Lock1 = Lock1;
        this.Lock2 = Lock2;
        super.setName(name);
    }

    @Override
    public void run() {
        while (true){
            synchronized (Lock1){
                synchronized (Lock2){
                    //通过sleep方法，让死锁更容易发生
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("有两个筷子啦，可以吃饭了！！");
                }
            }
        }
    }
}

class Chopstick2{
    private String name;
    public String getName() {
        return name;
    }
}