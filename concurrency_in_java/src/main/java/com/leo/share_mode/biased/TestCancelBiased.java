package com.leo.share_mode.biased;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class TestCancelBiased {
    static void cancel1() throws InterruptedException {
        ArrayList<Dog> list = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            log.debug("==================t1==================");
            for (int i = 0; i < 39; i++) {
                Dog dog = new Dog();
                list.add(dog);
                synchronized (dog) {
                    if (i==0)
                    log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                }
            }
            synchronized (list){
                list.notify();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (list){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("======================t2=====================");
            for (int i = 0; i < 39; i++) {
                Dog dog = list.get(i);
                if (i==10||i==21||i==39) {
                    log.debug("=========="+i+"=======");
                    log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                }
                synchronized (dog) {
                    if (i==10||i==21||i==39) {
                        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                    }
                }
                if (i==10||i==21||i==39) {
                    log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                }
            }
        });
        t2.start();
        t2.join();
        log.debug(SimpleClassLayout.parseInstance(new Dog()).toPrintSimple());
    }
    static void cancel2() throws InterruptedException {
        ArrayList<Dog> list = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            log.debug("======================t1=====================");
            for (int i = 0; i < 39; i++) {
                Dog dog = new Dog();
                list.add(dog);
                synchronized (dog) {
                    if (i==1)
                    log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                }
            }
            synchronized (list){
                list.notify();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (list){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("======================t2=====================");
            for (int i = 0; i < 39; i++) {
                Dog dog = list.get(i);
                if (i==10||i==21||i==50) {
                    log.debug("=========="+i+"=======");
                    log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                }
                synchronized (dog) {
                    if (i==10||i==21||i==50) {
                        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                    }
                }
                if (i==10||i==21||i==50) {
                    log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                }
            }
            synchronized (list){
                list.notify();
            }
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            synchronized (list){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("======================t3=====================");
            for (int i = 0; i < 39; i++) {
                Dog dog = list.get(i);
                if (i==10||i==21||i==38) {
                    log.debug("=========="+i+"=======");
                    log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                }
                synchronized (dog) {
                    if (i==10||i==21||i==38) {
                        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                    }
                }
                if (i==10||i==21||i==38) {
                    log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
                }
            }
            synchronized (list){
                list.notify();
            }
        });
        t3.start();

    }
    public static void main(String[] args) throws InterruptedException {
        cancel2();
    }
}
