package com.leo.concurrent_tool.thread_pool_jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class SubmitThreadApi {
    public static void submitTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService  = Executors.newFixedThreadPool(2);
        Future<String> future =executorService.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(1000);
                        return "ok";
                    }
                }
        );
        log.debug("{}",future.get());
    }


    public static void invokeAllTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService  = Executors.newFixedThreadPool(2);
        List<Future<Object>> futures = executorService.invokeAll(
                Arrays.asList(
                        () -> {
                            log.debug("begin");
                            Thread.sleep(1000);
                            return "1";
                        },
                        () -> {
                            log.debug("begin");
                            Thread.sleep(1000);
                            return "2";
                        },
                        () -> {
                            log.debug("begin");
                            Thread.sleep(1000);
                            return "3";
                        }
                )
        );

        futures.forEach(f->{
            try {
                System.out.println(f.get());;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

    public static void invokeAnyTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService  = Executors.newFixedThreadPool(2);
        Object o = executorService.invokeAny(
                Arrays.asList(
                        () -> {
                            log.debug("begin");
                            Thread.sleep(1000);
                            return "1";
                        },
                        () -> {
                            log.debug("begin");
                            Thread.sleep(500);
                            return "2";
                        },
                        () -> {
                            log.debug("begin");
                            Thread.sleep(1000);
                            return "3";
                        }
                )
        );

        System.out.println(o);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        invokeAnyTest();
    }
}
