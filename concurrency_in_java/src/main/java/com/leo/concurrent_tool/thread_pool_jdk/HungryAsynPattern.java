package com.leo.concurrent_tool.thread_pool_jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class HungryAsynPattern {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(
                ()->{
                    log.debug("处理点菜");
                    Future<String> food = executorService.submit(
                            () -> {
                                log.debug("开始做菜");
                                return "菜";
                            }
                    );
                    try {
                        String s = food.get();
                        log.debug("上菜:"+s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );
        executorService.submit(
                ()->{
                    log.debug("处理点菜");
                    Future<String> food = executorService.submit(
                            () -> {
                                log.debug("开始做菜");
                                return "菜";
                            }
                    );
                    try {
                        String s = food.get();
                        log.debug("上菜:"+s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
