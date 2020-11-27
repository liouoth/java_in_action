package com.leo.changeable_class.question;

import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class DateFormatQuestion {
    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0;i<10;i++){
            new Thread(()->{
                TemporalAccessor temp = df.parse("1951-09-11");
                log.debug("{}",temp.toString());
            }).start();
        }
    }
}
