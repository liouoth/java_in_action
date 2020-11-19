package com.leo.share_mode.GuardedSuspession.mail;

public class GuardedObject {
    private static int id;
    private Object response;

    public GuardedObject(int id) {
        this.id = id;
    }

    public static int getId() {
        return id;
    }

    public Object get(long timeout){
        synchronized (this){
            long begin = System.currentTimeMillis();
            long passTime = 0;
            while (response==null){
                try {
                    if(timeout-passTime<0){
                        System.out.println("已经超时");
                        break;
                    }
                    this.wait(timeout-passTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passTime = System.currentTimeMillis()-begin;
            }
            return this.response;
        }
    }

    public void put(Object response){
        synchronized (this){
            this.response = response;
            this.notify();
        }
    }
}
