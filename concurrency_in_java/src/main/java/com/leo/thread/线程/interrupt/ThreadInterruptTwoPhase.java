package com.leo.thread.线程.interrupt;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterruptTwoPhase {
    private static Thread monitor;

    public static void start() {
        log.debug("开始监控");
        monitor = new Thread(() -> {
            Thread temp = Thread.currentThread();
            temp.isInterrupted();
            Thread.interrupted();
            while (true) {
                if (temp.isInterrupted()) { break; }
                try { Thread.sleep(100); }
                catch (InterruptedException e) {
                    //需要将打断标记置为打断,没有设置方法只能使用interrupt试试
                    //如果不打断，因为阻塞状态打断时是false，那么catch到错误之后仍旧会无限循环
                    temp.interrupt(); //running状态调用，会置为true
                    e.printStackTrace();
                }
                log.debug("打印监控日志,是否被{}",temp.isInterrupted());
            }
        });
        monitor.start();
    }

    public static void stop() {
        monitor.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        start();
        Thread.sleep(2000);
        stop();
    }

}
