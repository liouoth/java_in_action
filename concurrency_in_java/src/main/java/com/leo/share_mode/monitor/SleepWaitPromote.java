package com.leo.share_mode.monitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepWaitPromote {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    static void step1() throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没？[{}]", hasCigarette);
                if (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) { //这样如果2秒内，烟送不大，则会释放锁，并且小南代码不会执行
                    log.debug("可以开始干活了");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    log.debug("可以开始干活了");
                }
            }, "其它人").start();
        }

        Thread.sleep(1000);
        new Thread(() -> {
            // 这里能不能加 synchronized (room)？不能，因为获得不了锁
            hasCigarette = true;
            log.debug("烟到了噢！");
        }, "送烟的").start();
    }
    //使用wait notify，可以让其他线程先执行,思考这样有问题吗？送烟的怎么知道叫醒的就是小南呢？
    static void step2() throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没？[{}]", hasCigarette);
                if (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) { //这样如果2秒内，烟送不大，则会释放锁，并且小南代码不会执行
                    log.debug("可以开始干活了");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    log.debug("可以开始干活了");
                }
            }, "其它人").start();
        }

        Thread.sleep(1000);
        new Thread(() -> {
            log.debug("烟到了噢！");
            hasCigarette = true;
            synchronized (room){
                room.notify();
            }
        }, "送烟的").start();
    }
    //虚假唤醒，notify是随机的，比如另外一个小女也在等待中，等待的是外卖
    static void step3() throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟了，可以开始干活了");
            }
        }, "小南").start();
        new Thread(() -> {
            synchronized (room) {
                log.debug("外卖到没？[{}]", hasCigarette);
                while (!hasTakeout) {
                    log.debug("没外卖，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有外卖了，可以开始干活了");
            }
        }, "小女").start();

        Thread.sleep(1000);
        new Thread(() -> {
            log.debug("烟到了噢！");
            hasCigarette = true;
            synchronized (room){
                room.notifyAll();
            }
        }, "送烟的").start();
    }
    public static void main(String[] args) throws InterruptedException {
        step3();
    }
}
