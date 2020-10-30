package com.leo.thread.线程;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ThreadTest {
    static Logger log = LoggerFactory.getLogger(ThreadTest.class);
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                log.debug("running");
            }
        };
        thread.setName("son thread");
        thread.start();
        log.debug("running");
    }
}
