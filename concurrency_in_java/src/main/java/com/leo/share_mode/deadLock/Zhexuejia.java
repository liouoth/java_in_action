package com.leo.share_mode.deadLock;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;

public class Zhexuejia {
    public static void main(String[] args) {
        for (int i =0;i<5;i++){
            Man man = new Man(i);
            man.setName("哲学家 "+i);
            man.start();
        }
    }
}
@Slf4j
class Man extends Thread{
    private int id;

    Man(int id){
        this.id = id;
    }
    @Override
    public void run() {
        while (true){
            synchronized (Chopstick.get(id)){
                synchronized (Chopstick.get((id+1)%4)){
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

class Chopstick{
   static Object [] chopsticks = {new Object(),new Object(),new Object(),new Object(),new Object()};

    public static Object get(int i){
        return chopsticks[i];
    }
}