package com.leo.concurrent_tool.thread_task;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FixedTimeTest {
    public static void main(String[] args) {
        //每周四18点定时执行任务
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
        LocalDateTime now = LocalDateTime.now();
        //测试使用今天的
        LocalDateTime thursday = now.with(DayOfWeek.WEDNESDAY).withHour(15).withMinute(39).withSecond(40).withNano(0);
        if (now.compareTo(thursday)>=0){
            thursday = thursday.plusWeeks(1);
        }
        //计算时间差，即执行时间
        long initailDelay = Duration.between(now,thursday).toMillis();
        System.out.println(initailDelay);
        //每周间隔
        long oneWeek = 7*24*3600*1000;
        threadPool.scheduleAtFixedRate(()->{
            log.debug("执行啦啦啦！！！！");
        },initailDelay,oneWeek,TimeUnit.MILLISECONDS);
    }
}
