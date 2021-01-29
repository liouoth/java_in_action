package com.leo.jmm.visibility;

public class VolatileTest {
    static int num = 0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println("Child:" + num);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num ++;
            System.out.println("Child End:" + num);
        }).start();
        System.out.println("Main11111-》:" + num);
        while (num == 0) {
            synchronized (VolatileTest.class){}
        }
        System.out.println("Main:" + num);
    }
}
class O{}