package com.leo.thread.线程;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class ThreadTest3 {
    static Logger log = LoggerFactory.getLogger(ThreadTest3.class);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running");
                return 100;
            }
        });
        new Thread(task).start();
        //主线程会陷入阻塞，之前一直有一个疑问这样和单线程有什么区别？
        //如果有多个task的话，就可以获取到多个结果进行判断了
        log.debug("{}",task.get());
    }
}
