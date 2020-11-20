package com.leo.share_mode.reentrantLock.syncreet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Son extends Far {
    public synchronized void sonDo(){
        log.debug("son do .....");
        sonAnotherDo();
        super.fatherDo();
    }

    private synchronized void sonAnotherDo() {
        log.debug("son another do .....");
    }

    public static void main(String[] args) {
        new Thread(()->{
            new Son().sonDo();
        },"一个线程").start();
    }
}
@Slf4j
class Far{

    public synchronized void fatherDo(){
        log.debug("father do .....");
    }
}
