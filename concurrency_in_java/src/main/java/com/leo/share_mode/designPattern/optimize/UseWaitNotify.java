package com.leo.share_mode.designPattern.optimize;

import org.openjdk.jol.util.ObjectUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程t1,t2,t3
 * t1 输出 a ,t2 输出 b , t3 输出 c
 * 交替输出5次 abcabcabcabcabc
 * 使用类封装去优化
 * 3个线程 执行了输出任务 我们可以将输出任务单独封装成类
 */
public class UseWaitNotify {
    public static void main(String[] args) {
        OutputWrapper wrapper = new OutputWrapper(1,5);//初始为1
        new Thread(
                ()->{
                    wrapper.print(1,2,"a");
                }
        ).start();
        new Thread(
                ()->{
                    wrapper.print(2,3,"b");
                }
        ).start();
        new Thread(
                ()->{
                    wrapper.print(3,1,"c");
                }
        ).start();
    }
}
class OutputWrapper{
    private int status; //不进入循环的条件
    private int circle; //循环的次数

    public OutputWrapper(int status, int circle) {
        this.status = status;
        this.circle = circle;
    }

    public void print(int waitFlag, int nextFlag, String output){
        for (int i = 0;i<circle;i++){
            synchronized (this){
                while (this.status!=waitFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(output);
                this.status = nextFlag;
                this.notifyAll();
            }
        }
    }

}
