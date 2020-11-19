package com.leo.share_mode.productAcustom;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class MsgQueue {
    //用一个队列容器存储消息
    private LinkedList<Message> queue = new LinkedList<>();
    //消息队列需要有容量上限
    private final int capacity = 3;

    //put
    public void put(Message message) {
        //首先需要判断消息队列，空不空.在这里我把while循环放在了外面，
        synchronized (queue) {
            while (queue.size() == capacity) {
                try {
                    log.debug("队列满了，没办法放入，需要等待");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(message);
            log.debug("队列放入：" + message.getContent());
            //这里如果遇到queue没有持有锁，那么怎么办，将这个加在synchronized里面，保证了queue操作并且保证了notifyAll
            queue.notifyAll();
        }
    }


    //take（消费）
    public Message take() {
        //首先需要判断消息队列，空不空
        while (queue.isEmpty()) {
            synchronized (queue) {
                try {
                    log.debug("队列空，没办法消费，需要等待");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return queue.removeFirst();
    }

}

class Message {
    private Integer id;
    private Object content;

    public Message(Integer id, Object content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public Object getContent() {
        return content;
    }
}
