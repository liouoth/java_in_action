package com.leo.share_mode.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class TestBiased {
    static void lock_delay() throws InterruptedException {
        Dog dog = new Dog();
        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
        Thread.sleep(4000);
        log.debug(SimpleClassLayout.parseInstance(new Dog()).toPrintSimple());
    }

    private static void biased_lock() {
        Dog dog = new Dog();
        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
        synchronized (dog){
            log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
        }
        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
        log.debug(SimpleClassLayout.parseInstance(new Dog()).toPrintSimple());
    }
    private static void hashcode_biased() {
        Dog dog = new Dog();
        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
        dog.hashCode();
        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
        synchronized (dog){
            log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
        }
        log.debug(SimpleClassLayout.parseInstance(dog).toPrintSimple());
    }

    //-XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws InterruptedException {
        biased_lock();
    }

}

class Dog{}