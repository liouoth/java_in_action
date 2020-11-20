package com.leo.share_mode.reentrantLock.syncreet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class syncMonitor {
    public static void main(String[] args) {
        new a().start();
    }
}
@Slf4j
class a extends Thread{
    private synchronized void aa(){
        log.debug("aa1");
    }
    @Override
    public void run() {
        synchronized (this){
            aa();
            log.debug("aa2");
        }
    }
}
