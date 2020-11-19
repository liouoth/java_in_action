package com.leo.share_mode.productAcustom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MQTest {
    public static void main(String[] args) throws InterruptedException {
        MsgQueue mq = new MsgQueue();
        for (int i = 0;i<10;i++){
            int id = i;
            new Thread(()->{
                try {
                    log.debug("正在拍！！！！");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mq.put(new Message(id,"小黄片"+id));
            },"生产者:"+i).start();
        }
        Thread.sleep(1000);
        new Thread(()->{
            while (true){
                Message message = mq.take();
                log.debug("下载到："+message.getContent()+" ,快来看小黄片！");
            }
        }).start();
    }
}
