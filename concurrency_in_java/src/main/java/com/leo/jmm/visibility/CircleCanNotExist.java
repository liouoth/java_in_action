package com.leo.jmm.visibility;

import lombok.extern.slf4j.Slf4j;

/**
 * 主线程中启动一个新线程，希望通过一个全局的标识符来操控子线程的运行。
 * 运行一段时间后，将标识符改动，希望子线程while循环能够停下来，能够如预想一样达到目的吗？
 */
@Slf4j
public class CircleCanNotExist {
    static boolean run = true;
//    static volatile boolean run = true;


    public static void main(String[] args) throws InterruptedException {
        printTest();
    }

    private static void printTest() throws InterruptedException{
        Object o = new Object();
        Thread thread = new Thread(
                () -> {
                    while (true) {
                        synchronized (o){
                            System.out.println("当前run："+run); //pronunce print line
                            if (run == false)
                                break;
                        }
                    }
                }
        );
        thread.start();
        log.debug("子线程状态:{}",thread.getState().name());
        Thread.sleep(1000);
        run = false;
        Thread.sleep(500);
        log.debug("子线程状态:{}",thread.getState().name());
    }

    private static void lockOptimize() throws InterruptedException{
        Object o = new Object();
        Thread thread = new Thread(
                () -> {
                    while (true) {
                        synchronized (o){
                            if (run == false)
                                break;
                        }
                    }
                }
        );
        thread.start();
        log.debug("子线程状态:{}",thread.getState().name());
        Thread.sleep(1000);
        run = false;
        Thread.sleep(500);
        log.debug("子线程状态:{}",thread.getState().name());
    }
    private static void origin() throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (true) {
                        if (run == false)
                            break;
                    }
                }
        );
        thread.start();
        log.debug("子线程状态:{}",thread.getState().name());
        Thread.sleep(2000);
        run = false;
        log.debug("子线程状态:{}",thread.getState().name());
    }
}
