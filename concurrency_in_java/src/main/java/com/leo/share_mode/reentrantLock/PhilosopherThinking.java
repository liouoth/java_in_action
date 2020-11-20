package com.leo.share_mode.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherThinking {
    static Chopsticks [] chopsticks = {new Chopsticks(),new Chopsticks(),new Chopsticks(),new Chopsticks(),new Chopsticks()};

    public static void main(String[] args) {
        for (int i =0;i<5;i++){
            new Philosopher("哲学家--"+i,chopsticks[i],chopsticks[(i+1)%5]).start();
        }
    }
}
@Slf4j
class Philosopher extends Thread{
    private Chopsticks left;
    private Chopsticks right;

    Philosopher(String name,Chopsticks left,Chopsticks right){
        this.left=left;
        this.right=right;
        super.setName(name);
    }

    @Override
    public void run() {
        while (true){
            try {
                if (left.tryLock()){ //不应该写等待
                    try {
                        if (right.tryLock()) {
                            try {
                                sleep(1000);
                                log.debug("获取到锁，开始吃饭！");
                            }finally {
                                right.unlock();
                            }
                        }
                    }finally {
                        left.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Chopsticks extends ReentrantLock {
}
