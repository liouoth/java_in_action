package com.leo.jmm.visibility;

import lombok.extern.slf4j.Slf4j;

/**
 * 双阶段终止模式
 * 旧模式:
 * -- 睡眠时打断 ，会抛出异常（wait sleep）
 *    主动获取当前线程并进行打断，标记为true
 * -- 运行时打断 ，标记为true
 * 缺点：
 * 必须时刻关注InterruptedException，容易遗漏，造成问题
 * 因为打断的话 都是主动的，之后有可能是开关 一个接口
 */
@Slf4j
public class RewriteTwoPhasePattern {
    public static void main(String[] args) throws InterruptedException {
        //一个程序
       Monitor monitor =  new Monitor();
       monitor.start();
       Thread.sleep(2000);
       monitor.stop();
    }
}
@Slf4j
class Monitor{
    private Thread monitor;
    private volatile boolean stop = false; //true 被打断 false 未被打断

    public void start(){
        monitor = new Thread(()->{
            Thread current = Thread.currentThread();
            while (!stop){  //直接将线程是否被打断的标志外置
                if (current.isInterrupted()){
                    stop = current.isInterrupted();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //执行监控代码
                log.debug("=========监控中========");
            }
        });
        monitor.start();
    }

    public void stop(){
        monitor.interrupt();
        this.stop = true;
        log.debug("=========打断啦！========");
    }
}
