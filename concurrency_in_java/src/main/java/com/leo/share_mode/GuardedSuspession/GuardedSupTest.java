package com.leo.share_mode.GuardedSuspession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuardedSupTest {
    public static void main(String[] args) {
        Guarded guarded = new Guarded();
        new Thread(()->{
            log.debug("我是下载器！！！");
            guarded.put(downloadTask());
        },"下载器").start();

        new Thread(()->{
            log.debug("我是色鬼,我要下载！！！");
            String video = (String) guarded.get(500);
            log.debug("fanhao:"+video);
        },"色鬼").start();

        for(int i = 0;i<100;i++){
            log.debug("下载好慢，好寂寞"+i);
        }
    }

    private static Object downloadTask(){
        String d = "小黄片";
        try {
            log.debug("下载任务正在进行中！！！");
            Thread.sleep(5000);
            log.debug("下载任务完成！！！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return d;
    }
}

class Guarded {
    private Object response;

    //那超时之后怎么让下载停止呢？
    public Object get(long timeout){
        synchronized (this){
            long begin = System.currentTimeMillis();
            long passTime = 0;//过去的时间
            while (response==null){
                try {
                    if(timeout-passTime<0){
                        System.out.println("已经超时");
                        break;
                    }
                    //那么应该wait多久呢，我一开始觉得应该timeout/10之类的，分成10分
                    //这是有错的，因为没想到会有虚假唤醒的情况，虚假唤醒之后，会执行一次时间判断
                    //进行到这肯定passTime-timeout>0，那么就继续等待剩余的时间
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
