package com.leo.concurrent_tool.thread_task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
public class ExceptionTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
//        threadPool.submit(
//                () -> {
//                    log.debug("异常来啦！！");
//                    try {
//                        int a = 1 / 0;
//                    }catch (Exception e){
//                        log.debug(e.getMessage());
//                    }
//                }
//        );
        Future<Boolean> submit = threadPool.submit(
                () -> {
                    log.debug("异常来啦！！");
                    int a = 1 / 0;
                    return true;
                }
        );
        submit.get();
    }
}
