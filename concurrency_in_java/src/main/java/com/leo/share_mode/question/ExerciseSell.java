package com.leo.share_mode.question;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j
public class ExerciseSell {
    public static void main(String[] args) {
        //创建售票窗口
        TicketWindow ticketWindow = new TicketWindow(100000);
        List<Thread> list = new ArrayList<>(2);
        // 用来存储买出去多少张票
        List<Integer> sellCount = new Vector<>();

        //模拟多人买票，通过线程模拟
        for (int i = 0; i < 3000; i++) {
            Thread t = new Thread(() -> {
                // 分析这里的竞态条件
                try {
                    Thread.sleep(20);
                    int count = ticketWindow.sell(randomAmount());
                    Thread.sleep(20);
                    sellCount.add(count);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            list.add(t);
            t.start();
        }

        list.forEach((t) -> {
            try {
                t.join(); //等待所有的线程都执行完
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 买出去的票求和
        log.debug("selled count:{}",sellCount.stream().mapToInt(c -> c).sum());
        // 剩余票数
        log.debug("remainder count:{}", ticketWindow.getCount());
    }

    // Random 为线程安全
    static Random random = new Random();

    // 随机 1~5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}
