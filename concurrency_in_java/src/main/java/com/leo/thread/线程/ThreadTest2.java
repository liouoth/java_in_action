package com.leo.thread.线程;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThreadTest2 {
    static Logger log = LoggerFactory.getLogger(ThreadTest2.class);
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        //直接将函数赋值给接口
//        Runnable runnable = ()->log.debug("running");
        new Thread(runnable).start();
        log.debug("running");
    }
}
